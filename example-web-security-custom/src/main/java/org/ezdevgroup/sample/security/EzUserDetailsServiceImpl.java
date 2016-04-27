package org.ezdevgroup.sample.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ezdevgroup.ezframework.security.EzUserDetails;
import org.ezdevgroup.ezframework.support.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sun.jndi.toolkit.url.UrlUtil;

public class EzUserDetailsServiceImpl extends org.ezdevgroup.ezframework.security.EzUserDetailsServiceImpl {
	private static Logger log = LoggerFactory.getLogger(EzUserDetailsServiceImpl.class);

	/**
	 * HTTP Basic Authentication 인증시 이쪽에서 처리
	 * @param username
	 * @param rowMapper
	 * @return
	 * @throws UsernameNotFoundException
	 * @auther ddakker 2015. 10. 6.
	 */
	@Override
	protected EzUserDetails getEzUserDetails(String username, RowMapper rowMapper) throws UsernameNotFoundException {
		Map<String, List<String>> parameterMap = new HashMap<>();
		parameterMap.put("userId", Arrays.asList(new String[]{username.split("@")[0]}));
		parameterMap.put("userType", Arrays.asList(new String[]{username.split("@")[1]}));
		return getEzUserDetails(parameterMap, rowMapper);
	}

	/**
	 * JSP Login Form 인증시 이쪽에서 처리
	 * @param parameterMap
	 * @param rowMapper
	 * @return
	 * @throws UsernameNotFoundException
	 * @auther ddakker 2015. 10. 6.
	 */
	@Override
	protected EzUserDetails getEzUserDetails(Map<String, List<String>> parameterMap, RowMapper rowMapper) throws UsernameNotFoundException {
		log.debug("parameterMap{}", parameterMap);

		EzUserDetails ezUserDetails = null;
		String userId 			= getParameter("userId", 	parameterMap);
		String userType 		= getParameter("userType", parameterMap);

		try {
			String userLoinQuery = userQuery;

			ezUserDetails = (EzUserDetails) this.jdbcTemplate.queryForObject(userLoinQuery, new Object[] { userId, userType }, rowMapper);
		} catch(Exception e) {
			log.error("e userId: {}, userType: {}", userId, userType, e);
		}

		if (ezUserDetails == null) {
			log.error("userId: {}, userType: {} 사용자 정보가 없거나 1개 초과일 수 있습니다.", userId, userType);
			throw new UsernameNotFoundException("사용자 정보가 에러");
		}
		return ezUserDetails;
	}

	@Override
	protected Set<GrantedAuthority> getGrantedAuthorities(String username) {
		Map<String, List<String>> parameterMap = new HashMap<>();
		parameterMap.put("loginType", Arrays.asList(new String[]{"I"}));
		parameterMap.put("userId", Arrays.asList(new String[]{username}));
		return getGrantedAuthorities(parameterMap);
	}

	protected Set<GrantedAuthority> getGrantedAuthorities(Map<String, List<String>> parameterMap) {
		String userAuthKey 		= getParameter("userId", 	parameterMap) + getParameter("userType", parameterMap);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		List<String> auths = this.jdbcTemplate.query(authoritiesQuery,
				new Object[] { userAuthKey }, new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString(1);
					}
				});

        if (auths != null) {
        	for(String auth : auths) {
        		grantedAuthorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return grantedAuthorities;
	}
}
