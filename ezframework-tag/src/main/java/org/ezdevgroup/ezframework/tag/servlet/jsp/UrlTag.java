package org.ezdevgroup.ezframework.tag.servlet.jsp;

import javax.servlet.ServletRequest;

import org.ezdevgroup.ezframework.tag.servlet.jsp.common.TagUtil;


/**
 * 프로젝트에 포함된 리소스나, 이미지서버와 같이 변경될수 있는 리소스 정보를 관리한다.
 * 		- Spring MVC에 의존한다.
 * @auther ddakker 2014. 5. 30.
 *
 */
public class UrlTag {

	/**
	 * 프로젝트에 포함된 리소스 위치를 리턴한다.
	 * 		- 현재는 ROOT
	 * 		  Spring <mvc:resources> 설정 정보로 인해 WAS Context정보는 필요없다.
	 * @param url
	 * @return
	 * @auther ddakker 2014. 5. 30.
	 */
	public static String resource(String value) {
		return value;
	}


	/**
	 * 사내 이미지 서버 정보를 포함하여 리턴한다.
	 * 		- HTTP, HTTPS를 고려하여 처리한다.
	 * @param value
	 * @return
	 * @auther ddakker 2014. 5. 30.
	 */
	public static String img(String value) {
		return TagUtil.getImageSSL() + value;
	}

	public static String img(String value, ServletRequest request) {
		return TagUtil.getImageSSL(request) + value;
	}

}