package org.ezdevgroup.ezframework.support.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpServletRequest 에 대한 정보를 처리한다.
 * @author ddakker 2015. 10. 8.
 */
public class RequestUtils {
	private static Logger log = LoggerFactory.getLogger(RequestUtils.Browser.class);
	
	public static enum Browser {FF, SFR, OR, IE5, IE6, IE7, IE8, IE9, IE10, IE11, CHROME, ETC} 
	
	public static Browser getBrowserType(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        
        if (userAgent.contains("Firefox")) {
        	return Browser.FF;
        } else if (userAgent.contains("Safari")) {
        	return Browser.SFR;
        } else if (userAgent.contains("Opera")) {
        	return Browser.OR;
        } else if (userAgent.contains("Windows") && userAgent.contains("MSIE 5.0")) {
        	return Browser.IE5;
        } else if (userAgent.contains("Windows") && userAgent.contains("MSIE 6.0")) {
        	return Browser.IE6;
        } else if (userAgent.contains("Windows") && userAgent.contains("MSIE 7.0")) {
        	return Browser.IE7;
        } else if (userAgent.contains("Windows") && userAgent.contains("Trident/4.0") && userAgent.contains("MSIE 8.0")) {
        	return Browser.IE8;
        } else if (userAgent.contains("Windows") && userAgent.contains("Trident/5.0") && userAgent.contains("MSIE 9.0")) {
        	return Browser.IE9;
        } else if (userAgent.contains("Windows") && userAgent.contains("Trident/6.0") && userAgent.contains("MSIE 10.0")) {
        	return Browser.IE10;
        } else if (userAgent.contains("Windows") && userAgent.contains("Trident/7.0")) {
        	return Browser.IE11;
        } else {
        	log.warn("userAgent: {}", userAgent);
        	return null;
        }
        
		
	}
}
