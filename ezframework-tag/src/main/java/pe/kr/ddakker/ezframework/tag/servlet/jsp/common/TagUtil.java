package pe.kr.ddakker.ezframework.tag.servlet.jsp.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 사용자 정의 공통유틸 Method별로 분리
 * @auther ljc727 2014. 6. 16.
 */
public class TagUtil {

	private static final String PROPERTIES_RESOURCE_PATH = "/properties/globals-properties.xml";

	private static final Properties APPLICATION_PROPERTIES = new Properties();

	static {
		loadProperties(APPLICATION_PROPERTIES, PROPERTIES_RESOURCE_PATH);
	}

	private static void loadProperties(Properties properties, String resourcePath) {
		InputStream is = null;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			is = loader.getResourceAsStream(resourcePath);

			if (resourcePath.toLowerCase().endsWith("xml")) {
				properties.loadFromXML(is);
			} else {
				properties.load(is);
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			if (is != null) try { is.close(); } catch (IOException e) {}
		}
	}

	/**
	 * 주소의 SSL 상태에 따라 http://, https:// 구분한다.
	 * @return http://{imgserver}, https://{imgserver}
	 * @auther ljc727 2014. 6. 16.
	 */
	public static String getImageSSL() {
		ServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getImageSSL(request);
	}

	/**
	 * 주소의 SSL 상태에 따라 http://, https:// 구분한다.
	 * @param request
	 * @return http://{imgserver}, https://{imgserver}
	 * @auther ljc727 2014. 6. 17.
	 */
	public static String getImageSSL(ServletRequest request) {
		return APPLICATION_PROPERTIES.getProperty("resource.imgEzwel" + (request.isSecure()?"s":""));
	}

}
