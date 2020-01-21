package org.ezdevgroup.ezframework.support.wrapper.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.ezdevgroup.ezframework.support.util.JsonUtils;
import org.ezdevgroup.ezframework.support.util.StringUtils;
import org.ezdevgroup.ezframework.support.wrapper.http.security.Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * 외부 시스템과 HTTP로 통신한다.
 * 	ex)
 * 		EzHttpClient httpClient = new EzHttpClient();
		httpClient.setUrl("http://url.com/test.ez");		// 호출 URL(GET 파라미터 가능)[필수]
		//httpClient.setMethod(EzHttpClient.GET);			// GET
		//httpClient.setMethod(EzHttpClient.POST);			// POST(Default)
		httpClient.setParams(params);						// 파라미터
		//httpClient.setSecurity(new Base64(true, true));// isEncode=파라미터, isDecode=결과(URL에 붙어 있는 데이터에 대해서는 인코딩 안함)
		httpClient.send();

		String resultData = httpClient.getResultString();	// 결과 문자열 데이터
		JSONObject json = httpClient.getResultJSON();		// JSONObject로 리턴[결과값이 JSON문자열 일경우]
		Document xmlDoc = httpClient.getResultDOM();		// w3c Document으로 리턴[결과값이 XML문자열 일경우]

		Vo vo = externalBean.getResultClass(Vo.class);		// 헤더 가져오기

		* 실제 통신 전 결과데이터를 미리 예상하고 꺽어 테스트 하는 방법
			- httpClient.setResult(결과 문자열);
			- // httpClient.send();	// 주석

 * @author ddakker 2014. 8. 7.
 *
 */
public class EzHttpClient {
	private Logger log = LoggerFactory.getLogger(EzHttpClient.class);

	public static final String CHARSET_UTF_8 	= "UTF-8";
	public static final String CHARSET_EUC_KR 	= "EUC-KR";
	public static final String CONTENT_HTML 	= "application/x-www-form-urlencoded;";
	public static final String CONTENT_JSON 	= "application/json";
	public static final String CONTENT_XML 		= "text/xml";
	public static final String GET 				= "get";
	public static final String POST 			= "post";
	public static final String HTML 			= "html";
	public static final String JSON 			= "json";
	public static final String JSONP 			= "jsonp";

	private CloseableHttpClient httpClient		= null;
	private HttpUriRequest 		httpRequest		= null;
	private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

	private String				url				= "";
	private String				method 			= EzHttpClient.GET;
	private Map<String, Object> params;
	private Object				body;

	private String				charset				= CHARSET_UTF_8;
	private String				accept				= null;
	private String				contentType			= null;
	private String				dataType			= HTML;


	private CookieStore 		cookies;
	private Map<String, String> headers;
	private UsernamePasswordCredentials credentials	= null;


	private Map<String, String>	responseHeaders;
	private CookieStore 		responseCookies;
	private String				resultString;

	private Encryption			encryption		= null;

	public EzHttpClient() {
	}

	public EzHttpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
		this.poolingHttpClientConnectionManager = poolingHttpClientConnectionManager;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * methos POST, GET 의 파라미터
	 * @param params
	 * @auther ddakker 2014. 12. 2.
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * Content Body 의 파라미터
	 * @param body
	 * @auther ddakker 2014. 12. 2.
	 */
	public void setBody(Object body) {
		this.body = body;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getAccept() {
		return accept;
	}

	/**
	 * 서버로 부터 리턴받을 데이터 타입
	 * @param accept
	 * @auther ddakker 2014. 12. 2.
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getContentType() {
		return contentType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * 클라이언트에서 서버로 보낼 데이터 타입
	 * @param contentType
	 * @auther ddakker 2014. 12. 2.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public CookieStore getCookies() {
		return cookies;
	}

	public void setCookies(CookieStore cookies) {
		this.cookies = cookies;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public UsernamePasswordCredentials getCredentials() {
		return credentials;
	}

	/**
	 * HTTP Basic Authentication ID/PWD
	 * @param credentials
	 * @auther ddakker 2015. 8. 26.
	 */
	public void setCredentials(UsernamePasswordCredentials credentials) {
		this.credentials = credentials;
	}

	public void setEncryption(Encryption encryption) {
		this.encryption = encryption;
	}

	public int send() {
		return send(this.url);
	}

	public int send(String urlParam) {
		this.url = urlParam;

		if (org.apache.commons.lang.StringUtils.isEmpty(url)) {
			throw new RuntimeException("Empty URL");
		}
		if (body != null && params != null) throw new RuntimeException("RequestBody, Parameter 중 한가지만 보낼 수 있습니다.");
		if (dataType == null)				throw new RuntimeException("dataType is null");

		if (JSON.equals(dataType.toLowerCase()) || JSONP.equals(dataType.toLowerCase())) {
			if (accept == null) {
				accept = CONTENT_JSON;
			}
			if (contentType == null) {
				contentType = CONTENT_JSON;
			}
		}
		if (body != null && contentType == null) {
			contentType = CONTENT_JSON;
		}

		int statusCode = -1;

		String jsseEnableSNIExtension = null;
		try {
			if (httpClient == null) {
				log.debug("url {}", url);
				/*HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager);
				if (isSSL(url)) {
					jsseEnableSNIExtension = System.getProperty("jsse.enableSNIExtension");
					System.setProperty("jsse.enableSNIExtension", "false");
				}

				httpClient = httpClientBuilder.build();*/

				HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager);
				if (isSSL(url)) {
					SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
					HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
					SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

					httpClientBuilder.setSSLSocketFactory(connectionFactory);
				}
				httpClient = httpClientBuilder.build();
			}
			HttpContext localContext = new BasicHttpContext();
			localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookies);

			List<NameValuePair> nvps 	= new ArrayList <NameValuePair>();
			String messageBodyJsonStr	= null;

			if (body != null) {
				if (body != null) {
					ObjectMapper om	= new ObjectMapper();
					messageBodyJsonStr 	= om.writeValueAsString(body);
				}
			}
			if (params != null) {
				Iterator<String> it = params.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					Object param = params.get(key)==null?new Object():params.get(key);
					if (param instanceof Object[] ) {
						for (Object val : (Object[]) param) {
							String value = String.valueOf(val);
							/*if (encryption != null && encryption.isInputEncode() == true ){
								value = encryption.encode(value);
							}*/
							nvps.add(new BasicNameValuePair(key, value));
						}
					} else {
						String value = String.valueOf(param.toString());
						/*if (encryption != null && encryption.isInputEncode() == true ){
							value = encryption.encode(value);
						}*/
						nvps.add(new BasicNameValuePair(key, value));
					}
				}
			}

			if (method.equals(EzHttpClient.GET) ){
				if (!nvps.isEmpty()) {
					url += "?" + URLEncodedUtils.format(nvps, charset);
				}

				HttpGet httpGet = new HttpGet(url);
				httpGet.setURI(new URI(url));

				httpRequest = httpGet;
			} else if (method.equals(EzHttpClient.POST) ){
				HttpPost httpPost = new HttpPost(url);
				httpPost.setURI(new URI(url));

				if (org.apache.commons.lang.StringUtils.isNotEmpty(messageBodyJsonStr)) {
					httpPost.setEntity(new StringEntity(messageBodyJsonStr, ContentType.create("text/plain", charset)));
				}
				if (!nvps.isEmpty()) {
					httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
				}

				httpRequest = httpPost;
			}
			if (accept != null) {
				httpRequest.setHeader(HttpHeaders.ACCEPT, accept);
			}
			if (contentType != null) {
				httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
			}

			if (headers != null) {
				Iterator<String> it = headers.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					httpRequest.setHeader(key, headers.get(key));
				}
			}
			if (credentials != null) {
				httpRequest.setHeader("Authorization", "Basic " + Base64.encodeBase64String((credentials.getUserName()+":"+credentials.getPassword()).getBytes()));
			}


			CloseableHttpResponse httpResponse = httpClient.execute(httpRequest, localContext);
			if (httpResponse != null) {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode == 302) {
					resultString = httpResponse.getFirstHeader("Location").getValue();
				} else {
					resultString = EntityUtils.toString(httpResponse.getEntity());

					/*if (encryption != null && encryption.isOutputDecode() == true) {
						resultString = encryption.decode(resultString);
					}*/
				}


				if (cookies == null) {
					cookies = new BasicCookieStore();
				}

				HeaderElementIterator it = new BasicHeaderElementIterator(httpResponse.headerIterator("Set-Cookie"));
				int i = 0;
				while (it.hasNext()) {
					HeaderElement elem = it.nextElement();
					BasicClientCookie responseCookie = new BasicClientCookie(elem.getName(), elem.getValue());
					responseCookie.setDomain(new URI(url).getHost());
					NameValuePair[] params = elem.getParameters();
					for (NameValuePair param : params) {
						for (Method m : responseCookie.getClass().getMethods()) {
							if (m.getName().indexOf("set") == 0 && m.getName().toLowerCase().indexOf(param.getName()) == 3) {
								try {
									m.invoke(responseCookie, param.getValue());
								} catch (Exception e) {
									log.error("{}", e);
								}
								break;
							}
						}
					}
					cookies.addCookie(responseCookie);
				}

				httpResponse.close();
			}
		} catch (Exception e) {
			log.error("HttpClient Call Fail {}", e);
		} finally {
			if (jsseEnableSNIExtension != null && isSSL(url)) {
				System.setProperty("jsse.enableSNIExtension", jsseEnableSNIExtension);
			}
		}

		return statusCode;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public CookieStore getResponseCookies() {
		return responseCookies;
	}

	public String getResultString() {
		return StringUtils.defaultString(resultString);
	}

	/**
	 * JSONObject에서 받아온 데이터를 clz 객체로 변환한다.
	 * @param clz
	 * @return
	 * @auther ddakker 2013. 6. 13.
	 */
	public <T> T getResultClass(Class<T> clz) {
		return JsonUtils.toJson(getResultString(), clz);
	}

	public Document getResultDOM() {
		return null;
	}

	private boolean isSSL(String url) {
		return (url != null && url.indexOf("https") == 0)?true:false;
	}
}
