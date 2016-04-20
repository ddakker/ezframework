package org.ezdevgroup.sample.security;

import java.util.ArrayList;
import java.util.List;

import org.ezdevgroup.sample.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.ezdevgroup.ezframework.security.EzUserDetails;

public class UserDetailsHelper {
	private static Logger log = LoggerFactory.getLogger(UserDetailsHelper.class);

		/**
		 * 인증된 사용자객체를 VO형식으로 가져온다.
		 * @return Object - 사용자 ValueObject
		 * @auther ddakker 2014. 6. 12.
		 */
		public static User getAuthenticatedUser() {
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();

			if (authentication == null) {
				return null;
			}
			EzUserDetails details = (EzUserDetails) authentication.getPrincipal();

			return (User) details.getUser();
		}

		/**
		 * 인증된 사용자의 권한 정보를 가져온다.
		 *
		 * @return
		 * @auther ddakker 2014. 6. 12.
		 */
		public static List<String> getAuthorities() {
			List<String> listAuth = new ArrayList<String>();

			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();

			if (authentication == null) {
				return null;
			}

			for( GrantedAuthority authoritie : authentication.getAuthorities() ){
				listAuth.add(authoritie.getAuthority());
			}

			return listAuth;
		}

		/**
		 * 인증된 사용자 여부를 체크한다.
		 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
		 * @auther ddakker 2014. 6. 12.
		 */
		public static Boolean isAuthenticated() {
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();

			if (authentication == null) {
				return Boolean.FALSE;
			}

			String username = authentication.getName();
			if (username.equals("anonymousUser")) {
				return Boolean.FALSE;
			}

			return authentication.getPrincipal()!=null;
		}
}
