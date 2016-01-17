package pe.kr.ddakker.ezframework.security.web.filter;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class EzUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static Logger log = LoggerFactory.getLogger(EzUsernamePasswordAuthenticationFilter.class);
	
	public static String IS_CUSTOM_PARAMETER_KEY = "*****is_custom_parameterMap*****";
	
	private ObjectMapper objectMapper = null;
	public EzUsernamePasswordAuthenticationFilter() {
		objectMapper = new ObjectMapper();
	}
	
	
	@Override
    protected String obtainUsername(HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("===== 파라미터 가공 처리");
			log.debug("===== request.paramsMap {}", request.getParameterMap());
			Enumeration<String> headerNmEnum = request.getHeaderNames();
		    while(headerNmEnum.hasMoreElements()){
		        String name = headerNmEnum.nextElement() ;
		        String val = request.getHeader(name) ;
		        log.debug("===== Header {}=[{}]", name, val) ;
		    }
		}

        String jsonUsername = "";
		try {
			Map<String, String[]> parameterMap = request.getParameterMap();
			parameterMap.put("*****comment*****", new String[]{"Spring Security 인증 파라미터 가공"});
			parameterMap.put(IS_CUSTOM_PARAMETER_KEY, null);
			jsonUsername = objectMapper.writeValueAsString(parameterMap);
			log.debug("===== 파라미터 가공 처리 완료 ===== jsonUsername {}", jsonUsername);
		} catch (Exception e) {
			throw new RuntimeException("JSON Style username 처리 실패", e);
		}
        return jsonUsername;
    }
}
