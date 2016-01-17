package pe.kr.ddakker.ezframework.security.access.interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import pe.kr.ddakker.ezframework.web.GlobalsProperties;

public class EzSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	private static Logger log = LoggerFactory.getLogger(EzSecurityInterceptor.class);

	@Resource private GlobalsProperties globalsProperties;

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, filterChain);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			//fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			filterChain.doFilter(fi.getRequest(), fi.getResponse());
		} catch (Exception e) {
			log.error("e: {}", e.toString());
			
			String redirectUri = ((HttpServletRequest) request).getContextPath() + globalsProperties.getProperty("page.login.form.url");
			
			if (e.getMessage().contains("org.apache.tiles.request.render.CannotRenderException") ||
				e.getMessage().contains("JasperException") ||
				e.getMessage().contains("JSPException")) {
				log.error("getContextPath: {}", ((HttpServletRequest) request).getContextPath());
				log.error("view.jsp.error: {}", globalsProperties.getProperty("page.error.500.url"));
				redirectUri = globalsProperties.getProperty("page.error.500.url");
				
				log.error("forwardJSP: {}", redirectUri);
				request.getRequestDispatcher(redirectUri).forward(request, response);
			} else {
				log.error("redirectURI: {}", redirectUri);
				fi.getResponse().sendRedirect(redirectUri);
			}
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return securityMetadataSource;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
}
