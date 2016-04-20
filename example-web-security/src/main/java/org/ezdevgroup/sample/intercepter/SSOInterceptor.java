package org.ezdevgroup.sample.intercepter;

import static org.ezdevgroup.sample.security.SecurityHelper.setSpringSecurityContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.ezdevgroup.ezframework.security.EzUserDetailsServiceImpl;

/**
 * 사용자 인증 처리
 * @author ddakker
 *
 */
@Resource
public class SSOInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(SSOInterceptor.class);

	@Resource
	private EzUserDetailsServiceImpl ezUserDetailsService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (true) {	// 로그인이 안되어 있거나, 사용자가 변경되었다면
			String userId = getUserId();
			
			UserDetails userDetails = ezUserDetailsService.loadUserByUsername(userId);
			setSpringSecurityContext(request, response, new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("BcRoleChangeInterceptor.postHandle() E");
	}
	
	private String getUserId() {
		// ------------------------------------------
		// 이 부분은 차 후 인터페이스를 통해서 넘어올 것입니다.
		// 복지관과 인터페이스가 안될것이기 때문에 이 부분을 수정하여 사용자 로그인 처리 합니다.
		String userKey 	= "1000007856";		// 복지관 식별번호
		String userNm 	= "테스터";			// 복지관 유저명
		String clientCd	= "ez1";			// 고객사코드
		String useType	= "N";				// 테스트계정 여부[N=사용자, Y=테스터]
		// ------------------------------------------
		
		// userKey 는 복지관 식별 번호입니다.
		// 해당 정보가 E쿠폰 사용자 테이블에 없을 경우 Insert
		// userId는 E쿠폰 개별적으로 유니크하게 생성합니다.
		// E쿠폰 사용자 테이블에 존재 한다면 userId 를 리턴합니다.
		// 위 정보를 E쿠폰 사용자 테이블에 추가합니다.
		
		String userId = "user";	// E쿠폰 사용자 테이블에서 userKey를 바탕으로 조회 후 리턴
		return userId;
	}
	

}