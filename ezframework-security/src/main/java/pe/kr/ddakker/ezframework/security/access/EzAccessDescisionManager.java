package pe.kr.ddakker.ezframework.security.access;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import pe.kr.ddakker.ezframework.security.SecurityHelper;
import pe.kr.ddakker.ezframework.security.web.EzSecurityControllerAdvice;

/**
 * 인증 사용자의 권한 엑세스 제어
 * @author ddakker 2014. 8. 28.
 *
 */
public class EzAccessDescisionManager implements AccessDecisionManager {

	private static Logger log = LoggerFactory.getLogger(EzAccessDescisionManager.class);

	@Override
	public void decide(Authentication authentication, Object obj, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

		if(configAttributes != null) {
			Iterator<ConfigAttribute> it = configAttributes.iterator();
			while(it.hasNext()) {
				String needAuth = it.next().getAttribute();
				for(GrantedAuthority ga:authentication.getAuthorities()){
	                if(needAuth.equals(ga.getAuthority())){
	                    return;
	                }
	            }
			}
		}
		
		
		try {
			if (obj instanceof FilterInvocation) {
				HttpServletRequest request = ((FilterInvocation) obj).getHttpRequest();
				HttpServletResponse response = ((FilterInvocation) obj).getHttpResponse();
				
				if ( SecurityHelper.isXRequestedWith(request) || SecurityHelper.isAuthorization(request) || SecurityHelper.isContentTypeJson(request) || SecurityHelper.isContentTypeJsonp(request) || SecurityHelper.isContentTypeXml(request)) {
					request.getRequestDispatcher(EzSecurityControllerAdvice.IS_NOT_AUTHENTICATION).forward(request, response);
				}
			}
		} catch (ServletException | IOException e) {
			log.warn("예외 호출 케이스 처리 실패", e);
		}

        throw new AccessDeniedException("인증되지 않은 사용자입니다.");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
