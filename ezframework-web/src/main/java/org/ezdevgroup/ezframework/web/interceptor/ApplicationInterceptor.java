package org.ezdevgroup.ezframework.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ezdevgroup.ezframework.web.GlobalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 일반적인 공통 처리 인터셉터
 * @author ddakker
 *
 */
public class ApplicationInterceptor extends HandlerInterceptorAdapter {
	private Logger log = LoggerFactory.getLogger(ApplicationInterceptor.class);

	@Resource
	private GlobalProperties globalsProperties;

	/*@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}*/

	@SuppressWarnings("rawtypes")
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			if (org.apache.commons.lang.StringUtils.indexOf(request.getHeader("accept"), "text/html")  == -1) {
				modelAndView.addObject(globalsProperties.getProperty("content.key.result.code"), globalsProperties.getProperty("content.value.success"));
				modelAndView.addObject(globalsProperties.getProperty("content.key.result.message"), "");
			}
		}
	}
}