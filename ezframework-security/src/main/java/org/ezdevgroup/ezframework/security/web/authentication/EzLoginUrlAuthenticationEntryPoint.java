
package org.ezdevgroup.ezframework.security.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.ezdevgroup.ezframework.security.SecurityHelper;
import org.ezdevgroup.ezframework.security.web.EzSecurityControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Ajax 및 Basic Authorization 통신이라면 redirect 시키지 않고, 바로 Forward 시킨다.
 * @author ddakker 2015. 8. 26.
 */
public class EzLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	private static Logger log = LoggerFactory.getLogger(EzLoginUrlAuthenticationEntryPoint.class);
    
    public EzLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
		if ( SecurityHelper.isXRequestedWith(request) || SecurityHelper.isAuthorization(request) || SecurityHelper.isContentTypeJson(request) || SecurityHelper.isContentTypeJsonp(request) || SecurityHelper.isContentTypeXml(request)) {
			super.setUseForward(true);
			String loginFormUrl = super.getLoginFormUrl();
			super.setLoginFormUrl(EzSecurityControllerAdvice.IS_NOT_AUTHENTICATION);
			
			super.commence(request, response, authException);
			
			super.setUseForward(false);
			super.setLoginFormUrl(loginFormUrl);
		} else {
			super.commence(request, response, authException);
		}
    }
}
