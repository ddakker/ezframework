package pe.kr.ddakker.ezframework.web.interceptor.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 메서드 호출 앞뒤로 로그남기기
 * @auther ddakker 2013. 5. 15.
 */
public class LoggingAspect {
	private Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	private static final String CONTROLLER 	= "CONTROLLER";
	private static final String SERVICE 	= "SERVICE";
	private static final String COMPONENT 	= "COMPONENT";
	private static final String DAO 		= "DAO";

	@Autowired(required=false)
	private HttpServletRequest request;

	public void logBefore(JoinPoint joinPoint) {
		//log.debug("logBefore()");
	}

	public void logAfter(JoinPoint joinPoint) {
		//log.debug("logAfter()");
	}

	public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
		beforeLog(CONTROLLER, joinPoint);
		Object returnObj = joinPoint.proceed();
		afterLog(CONTROLLER, joinPoint);

		return returnObj;
	}

	public Object serviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
		beforeLog(SERVICE, joinPoint);
		Object returnObj = joinPoint.proceed();
		afterLog(SERVICE, joinPoint);

		return returnObj;
	}
	
	public Object componentAround(ProceedingJoinPoint joinPoint) throws Throwable {
		beforeLog(COMPONENT, joinPoint);
		Object returnObj = joinPoint.proceed();
		afterLog(COMPONENT, joinPoint);

		return returnObj;
	}

	public Object daoAround(ProceedingJoinPoint joinPoint) throws Throwable {
		beforeLog(DAO, joinPoint);
		Object returnObj = joinPoint.proceed();
		afterLog(DAO, joinPoint);

		return returnObj;
	}

	private void beforeLog(String layer, ProceedingJoinPoint joinPoint) throws Throwable {
		String classNm 		= joinPoint.getThis().toString();
		String methodNm 	= joinPoint.getSignature().getName();

		log.debug("----- START {} CLASS=[{}], METHOD=[{}]", layer, classNm, methodNm);
		if( log.isDebugEnabled() ){
			if( layer.equals(CONTROLLER) ){
				log.debug("----------- URL \t [{}]", request.getRequestURI());
				Enumeration en = request.getParameterNames();
				while( en.hasMoreElements() ){
					String key = (String) en.nextElement();
					for( String val : request.getParameterValues(key) ) {
						log.debug("----------- PARAM \t {}\t\t=[{}]", key, val);
					}
				}
			}else{
				for( int i=0,size=joinPoint.getArgs().length; i<size; i++ ){
					log.debug("----------- METHOD {} arguments[{}]={}", layer, i, joinPoint.getArgs()[i]);
				}
			}
		}
	}

	private void afterLog(String layer, ProceedingJoinPoint joinPoint) throws Throwable {
		String classNm 		= joinPoint.getThis().toString();
		String methodNm 	= joinPoint.getSignature().getName();
		long currentTime 	= System.currentTimeMillis();

		log.debug("----- END {} CLASS=[{}], METHOD=[{}], [소요시간: {}밀리초]", layer, classNm, methodNm, System.currentTimeMillis()-currentTime);
	}

}
