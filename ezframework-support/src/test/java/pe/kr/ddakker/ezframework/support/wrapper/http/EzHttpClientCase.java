package pe.kr.ddakker.ezframework.support.wrapper.http;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.kr.ddakker.ezframework.support.wrapper.http.EzHttpClient;
import pe.kr.ddakker.ezframework.support.wrapper.http.security.impl.Base64Encryption;



public class EzHttpClientCase {
	private Logger log = LoggerFactory.getLogger(EzHttpClient.class);

	@Test
	public void test404_에러_페이지() {
		EzHttpClient http = new EzHttpClient();
		http.setUrl("http://withus.ezwel.com/adfasdf");
		int statusCode = http.send();

		System.out.println("http.getStatusCode(): " + statusCode);
		assertThat("404여부", statusCode, is(404));
	}

	@Test
	public void testSession_세션_유지() {
		EzHttpClient http = new EzHttpClient();
		http.setUrl("http://shop.ezwel.com/web/login/loginForm.ez?userKey=MTAwMDEyMjcxNQ%3D%3D&empNum=MzI4Mjc5ODA%3D&cspCd=ZXpzaG9w&clientCd=d2l0순서 있는 목록aHVz&rrn=MTIzNzg5LTEzODEyMTI%3D&userNm=xde9usXN&homeTel=MDItMTExLTEyMzQ%3D&mobile=MDEwLTAwMDAtMDAwMA%3D%3D&email=dGVzdDFAZXp3ZWwuY29t&post=MTUyLTg0OA%3D%3D&addr1=vK2%2F7yCxuLfOsbggsbi3zrW%2F&addr2=dGVzdCjA%2FbTruei827HdwfYp&goUrl=&isReserveUse=dHJ1ZQ%3D%3D&ezMilUse=WQ%3D%3D&reserveRt=&aspOrderNum=&orderNum=&goodsCd=&orderGoodsSeq=&commSearchYn=WQ%3D%3D");
		int statusCode = http.send();

		System.out.println("http.getStatusCode(): " + statusCode);
		assertThat("로그인 정상 여부", http.getResultString().indexOf("/mainFstDepth.ez"), greaterThan(-1));




		statusCode = http.send("http://shop.ezwel.com/shopNew/main/mainFstDepth.ez");
		System.out.println("http.getStatusCode(): " + statusCode);
		assertThat("로그인 후 메인 페이지 정상 여부", http.getResultString().indexOf("/logo_shop.jpg"), greaterThan(-1));
	}

	@Test
	public void testSession_세션_유지test() {
		EzHttpClient http = new EzHttpClient();
		http.setUrl("http://222.231.44.209/redmine/login");
		int statusCode = http.send();

		System.out.println("1http.getStatusCode(): " + statusCode);


		Map<String, Object> params = new HashMap<String, Object>();
		params.put("utf8", "%E2%9C%93");
		params.put("authenticity_token", "DyrksA%2FW60C2H4Lmhp4YzvpjFcsp3bYto1%2F%2FnlRUgl8%3D");
		params.put("back_url", "http%3A%2F%2Fverify.ezwel.com%2Fredmine%2Faccount%2Fregister");
		params.put("username", "ddakker");
		params.put("password", "14745100");
		params.put("login", "%EB%A1%9C%EA%B7%B8%EC%9D%B8+%C2%BB");

		http.setParams(params);
		statusCode = http.send("http://222.231.44.209/redmine/login");

		System.out.println("2http.getStatusCode(): " + statusCode);

		//statusCode = http.send("http://verify.ezwel.com/redmine/issues/1157");
		statusCode = http.send("http://222.231.44.209/redmine");
		System.out.println("3http.getStatusCode(): " + statusCode);

		System.out.println("----1: " + http.getResultString());

	}

	@Test
	public void testCookieSetGet_쿠키_설정_및_가져오기() {
		CookieStore cookies = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("xeak", "6dd511c2978b5d1e5e3ebe3d65950ec6");
		cookie.setDomain(".ezwel.com");
		cookies.addCookie(cookie);

		EzHttpClient http = new EzHttpClient();
		http.setUrl("http://intranet.ezwel.com/zbxe/?document_srl=161206");
		http.setCookies(cookies);
		int statusCode = http.send();

		assertThat("쿠키 셋팅으로 정상 로그인여부", http.getResultString().indexOf("pw["), greaterThan(-1));


		http = new EzHttpClient();
		http.setUrl("http://ezcafe.ezwel.com/login/loginAction.ez?userBean.cookieSaveYn=on&userBean.userId=20111085&userBean.userPwd=lee1474");
		statusCode = http.send();

		assertThat("로그인 통신 정상 여부", statusCode, is(HttpStatus.SC_OK));

		boolean isKeyEzcafe = false;
		for (Cookie c : http.getCookies().getCookies()) {
			if (c.getName().equals("ezcafe")) {
				isKeyEzcafe = true;
				break;
			}
		}
		assertThat("로그인 쿠키 정상 여부", isKeyEzcafe, is(true));
	}

	@Test
	public void testSecurity_암호화() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userKey", "1000122715");


		EzHttpClient http = new EzHttpClient();
		http.setUrl("http://shop.ezwel.com/web/login/loginForm.ez?empNum=MzI4Mjc5ODA%3D&cspCd=ZXpzaG9w&clientCd=d2l0aHVz&rrn=MTIzNzg5LTEzODEyMTI%3D&userNm=xde9usXN&homeTel=MDItMTExLTEyMzQ%3D&mobile=MDEwLTAwMDAtMDAwMA%3D%3D&email=dGVzdDFAZXp3ZWwuY29t&post=MTUyLTg0OA%3D%3D&addr1=vK2%2F7yCxuLfOsbggsbi3zrW%2F&addr2=dGVzdCjA%2FbTruei827HdwfYp&goUrl=&isReserveUse=dHJ1ZQ%3D%3D&ezMilUse=WQ%3D%3D&reserveRt=&aspOrderNum=&orderNum=&goodsCd=&orderGoodsSeq=&commSearchYn=WQ%3D%3D");
		http.setParams(params);
		http.setEncryption(new Base64Encryption(true, false));
		int statusCode = http.send();

		assertThat("파라미터 암호화 정상 여부", http.getResultString().indexOf("/mainFstDepth.ez"), greaterThan(-1));
	}

	@Test
	public void testJson_객체주고받기() {
		BodyData bodyDataParam = new BodyData();
		bodyDataParam.setName("ddakker");
		bodyDataParam.setAge(30);

		EzHttpClient http = new EzHttpClient();
		http.setUrl("http://localhost:8080/sample/messageBody");
		http.setMothod(EzHttpClient.POST);
		http.setContentType(EzHttpClient.CONTENT_JSON);
		http.setAccept(EzHttpClient.CONTENT_JSON);
		http.setBody(bodyDataParam);	// HashMap도 가능
		int statusCode = http.send();

		System.out.println("statusCode: " + statusCode);
		System.out.println("data: " + http.getResultString());

		ResponseData responseData = http.getResultClass(ResponseData.class);
		System.out.println("responseData: " + responseData);

		assertThat("객체 통신 정상 여부", responseData.getResult(), is("0000"));

		if (responseData != null) {
			System.out.println("responseData: " + responseData.getResult());
			System.out.println("responseData: " + responseData.getMessage());
			ResultSampleBody sesultSampleBody = responseData.getSampleBodyVo();
			if (sesultSampleBody != null) {
				System.out.println("sesultSampleBody.name: " + sesultSampleBody.getName());
				System.out.println("sesultSampleBody.age: " + sesultSampleBody.getAge());
			}
		}
	}

}
