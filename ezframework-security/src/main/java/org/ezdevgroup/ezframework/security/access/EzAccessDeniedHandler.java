package org.ezdevgroup.ezframework.security.access;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.ezdevgroup.ezframework.security.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class EzAccessDeniedHandler implements AccessDeniedHandler {
	private static Logger log = LoggerFactory.getLogger(EzAccessDeniedHandler.class);
	
	private String redirectPage;

	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

	public String getRedirectPage() {
		return redirectPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String redirectURI = request.getContextPath() + redirectPage;
		log.debug("===== AccessDenied contextPath: " + request.getContextPath());
		log.debug("===== AccessDenied redirectPage: " + redirectPage);
		log.debug("===== AccessDenied redirectURI: " + redirectURI);
		
		if ( SecurityHelper.isXRequestedWith(request) || SecurityHelper.isAuthorization(request) || SecurityHelper.isContentTypeJson(request) || SecurityHelper.isContentTypeJsonp(request) || SecurityHelper.isContentTypeXml(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURI);
            dispatcher.forward(request, response);
		} else {
			response.sendRedirect(redirectURI);
		}
	}
}
