package pe.kr.ddakker.ezframework.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pe.kr.ddakker.ezframework.web.GlobalsProperties;

/**
 * 모든 Exception을 모아 처리한다.
 *
 * @author ddakker 2014. 6. 10.
 *
 */
@Controller
@ControllerAdvice
public class EzControllerAdvice {
	private Logger log  = LoggerFactory.getLogger(EzControllerAdvice.class);

	@Resource private GlobalsProperties globalsProperties;

	@ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		String viewJspError = globalsProperties.getProperty("view.jsp.error");
		String contentValueException = globalsProperties.getProperty("content.value.exception");
		log.error("데이터 저장소 처리중 문제가 발생하였습니다.", ex);
		log.error("viewJsp: {}, contentValueException: {}", viewJspError, contentValueException);
		
		ModelAndView mv = new ModelAndView(viewJspError);
        mv.addObject(globalsProperties.getProperty("content.key.result.code"), contentValueException);
        mv.addObject(globalsProperties.getProperty("content.key.result.message"), "데이터 저장소 처리중 문제가 발생하였습니다.");		// DB정보 노출문제
        return mv;
    }

	@ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException ex) {
		String viewJspError = globalsProperties.getProperty("view.jsp.error");
		String contentValueException = globalsProperties.getProperty("content.value.exception");
		log.error("RuntimeException", ex);
		log.error("viewJsp: {}, contentValueException: {}",viewJspError, contentValueException);
		
		ModelAndView mv = new ModelAndView(viewJspError);
        mv.addObject(globalsProperties.getProperty("content.key.result.code"), contentValueException);
        mv.addObject(globalsProperties.getProperty("content.key.result.message"), ex.getMessage());
        return mv;
    }

	@ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
		String viewJspError = globalsProperties.getProperty("view.jsp.error");
		String contentValueException = globalsProperties.getProperty("content.value.exception");
		log.error("Exception", ex);
		log.error("viewJsp: {}, contentValueException: {}",viewJspError, contentValueException);
		
        ModelAndView mv = new ModelAndView(viewJspError);
        mv.addObject(globalsProperties.getProperty("content.key.result.code"), contentValueException);
        mv.addObject(globalsProperties.getProperty("content.key.result.message"), ex.getMessage());
        return mv;
    }
	
}
