package org.ezdevgroup.sample.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.ezdevgroup.ezframework.web.GlobalProperties;
import org.ezdevgroup.ezframework.security.EzUserDetailsServiceImpl;

@Controller
public class LoginController {
	@Resource
	private GlobalProperties globalProperties;

	@Resource
	private EzUserDetailsServiceImpl ezUserDetailsService;

	/**
	 * 그룹웨어와 로그인 연동
	 * @param request
	 * @param response
	 * @param redirectUrl
	 * @param model
	 * @return
	 * @auther ddakker 2014. 8. 1.
	 */
	@RequestMapping(value="/login/form", method=RequestMethod.GET)
	public String form(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="redirectUrl", defaultValue="") String redirectUrl, @RequestParam(value="tempLoginId", defaultValue="") String tempLoginId, Model model) {
		return "login/loginForm";
	}

	/**
	 * 스프링 수동 로그인 처리
	 * @param userId
	 * @return
	 * @auther ddakker 2014. 7. 24.
	 */
	private Authentication login(String userId) {
		UserDetails userDetails = ezUserDetailsService.loadUserByUsername(userId);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}


}
