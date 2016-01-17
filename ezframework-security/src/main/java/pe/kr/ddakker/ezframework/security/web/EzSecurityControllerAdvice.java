package pe.kr.ddakker.ezframework.security.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.kr.ddakker.ezframework.web.GlobalsProperties;

@Controller
@ControllerAdvice
public class EzSecurityControllerAdvice {
	private static Logger log = LoggerFactory.getLogger(EzSecurityControllerAdvice.class);
	
	public static final String ACCESS_DENIED 			= "/login/accessDenied";
	public static final String IS_NOT_AUTHENTICATION 	= "/login/isNotAuthentication";
	public static final String AUTHENTICATION_FAILURE 	= "/login/authenticationFailure";
	
	
	@Resource private GlobalsProperties globalsProperties;
	
	/**
	 * 권한이 없는 요청
	 * @param model
	 * @return
	 * @auther ddakker 2014. 6. 10.
	 */
	@RequestMapping(value=ACCESS_DENIED)
	public void accessDenied(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws AccessDeniedException {
		log.debug("==== Access is denied(권한이 없는 요청입니다)");
		throw new AccessDeniedException("Access is denied(권한이 없는 요청입니다)");
	}
	
	/**
	 * 로그인 되지 않음
	 * @param model
	 * @return
	 * @auther ddakker 2014. 6. 10.
	 */
	@RequestMapping(value=IS_NOT_AUTHENTICATION)
	public void isNotAuthentication(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws AccessDeniedException {
		throw new BadCredentialsException("Is Not Authentication(로그인 되지 않았습니다.)");
	}

	/**
	 * 사용자 정보 불일치
	 * @param model
	 * @return
	 * @auther ddakker 2014. 6. 10.
	 */
	@RequestMapping(value=AUTHENTICATION_FAILURE)
	public void authenticationFailure(ModelMap model) throws AccessDeniedException {
		throw new BadCredentialsException("Bad credentials(사용자 정보가 잘못 되었습니다.)");
	}

	@ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(AccessDeniedException ex) {
		ModelAndView mv = new ModelAndView(globalsProperties.getProperty("view.jsp.notauth"));
        mv.addObject(globalsProperties.getProperty("content.key.result.code"), globalsProperties.getProperty("content.value.notauth"));
        mv.addObject(globalsProperties.getProperty("content.key.result.message"), ex.getMessage());
        return mv;
    }

	@ExceptionHandler(BadCredentialsException.class)
    public ModelAndView handleBadCredentialsException(BadCredentialsException ex) {
		ModelAndView mv = new ModelAndView(globalsProperties.getProperty("view.jsp.failauth"));
        mv.addObject(globalsProperties.getProperty("content.key.result.code"), globalsProperties.getProperty("content.value.badcreden"));
        mv.addObject(globalsProperties.getProperty("content.key.result.message"), ex.getMessage());
        return mv;
    }
}
