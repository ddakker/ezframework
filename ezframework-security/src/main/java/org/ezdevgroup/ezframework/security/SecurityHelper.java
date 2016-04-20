package org.ezdevgroup.ezframework.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class SecurityHelper {
	public static boolean isXRequestedWith(HttpServletRequest request) {
		return StringUtils.defaultString(request.getHeader("X-Requested-With")).contains("XMLHttpRequest");
	}
	
	public static boolean isAuthorization(HttpServletRequest request) {
		return StringUtils.defaultString(request.getHeader("Authorization")).contains("Basic");
	}
	
	public static boolean isContentTypeXml(HttpServletRequest request) {
		return StringUtils.defaultString(request.getHeader("Content-Type")).contains("application/xml");
	}
	
	public static boolean isContentTypeJson(HttpServletRequest request) {
		return StringUtils.defaultString(request.getHeader("Content-Type")).contains("application/json");
	}
	
	public static boolean isContentTypeJsonp(HttpServletRequest request) {
		return StringUtils.defaultString(request.getHeader("Content-Type")).contains("text/javascript");
	}
}
