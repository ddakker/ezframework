package org.ezdevgroup.ezframework.security;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.ezdevgroup.ezframework.security.web.filter.EzUsernamePasswordAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 인증 처리
 * @author ddakker 2014. 8. 28.
 *
 */
public class EzUserDetailsServiceImpl implements UserDetailsService{

	private static Logger log = LoggerFactory.getLogger(EzUserDetailsServiceImpl.class);

	protected List<String> ipList = new ArrayList<>();

	@Autowired
	protected HttpServletRequest request;


	protected JdbcTemplate jdbcTemplate;

	private RoleHierarchy roleHierarchy;

	protected String userQuery;
	protected String authoritiesQuery;

	private String mapClass;

	protected String [] ipCheckDomains;

	protected String ipCheckQuery;

	private ObjectMapper objectMapper = null;
	public EzUserDetailsServiceImpl() {
		objectMapper = new ObjectMapper();
	}

	/**
     * 사용자 테이블의 쿼리 조회 컬럼과 세션에서 사용할 사용자 VO와 메핑 할 클래스를 지정한다.
     * @param mapClass String
     */
    public void setMapClass(String mapClass) {
    	this.mapClass = mapClass;
    }


	public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}

	public void setAuthoritiesQuery(String authoritiesQuery) {
		this.authoritiesQuery = authoritiesQuery;
	}

	public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
		this.roleHierarchy = roleHierarchy;
	}

	public void setIpCheckDomains(String ipCheckDomains []) {
		this.ipCheckDomains = ipCheckDomains;
	}

	public void setIpCheckQuery(String ipCheckQuery) {
		this.ipCheckQuery = ipCheckQuery;
	}

	@PostConstruct
	private void init() {
		if (StringUtils.isNotEmpty(ipCheckQuery)) {
			List<Map<String , Object>> ipList = this.jdbcTemplate.queryForList(ipCheckQuery);
			for (Map<String , Object> map : ipList) {
				this.ipList.add((String) map.get("IP"));
			}
		}
	}

	/**
	 * 인증 처리하기
	 * @param userId
	 * @auther ddakker 2014. 8. 28.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RowMapper rowMapper = null;
		Class<?> clazz;
		try {
			clazz = ClassUtils.getClass(this.mapClass);

			boolean isJdbcTemplateConst = false;
			for (Constructor c : clazz.getConstructors()) {
				if (c.getParameterTypes().length == 1) {
					for (Class p : c.getParameterTypes()) {
						if ("org.springframework.jdbc.core.JdbcTemplate".equals(p.getName())) {
							isJdbcTemplateConst = true;
							break;
						}
					}
					if (isJdbcTemplateConst) {
						break;
					}
				}
			}
			if (isJdbcTemplateConst) {
				Constructor <?> constructor = clazz.getConstructor(JdbcTemplate.class);
				rowMapper = (RowMapper) constructor.newInstance(jdbcTemplate);
			} else {
				Constructor <?> constructor = clazz.getConstructor();
				rowMapper = (RowMapper) constructor.newInstance();
			}
		} catch (Exception e) {
			log.error("업무별 사용자정보 맵핑 클래스 생성 실패", e);
		}

		if (rowMapper == null) {
			throw new UsernameNotFoundException("업무별 사용자 정보 맵핑 클래스가 엄씀");
		}

		EzUserDetails ezUserDetails = null;
		// 인증된 사용자의 권한 불러오기
		Collection<GrantedAuthority> tmpGrantedAuthorities = null;

		log.info("========== username: [{}], isCustomParameterKey: [{}]", username, username.contains(EzUsernamePasswordAuthenticationFilter.IS_CUSTOM_PARAMETER_KEY));
		if (username.contains(EzUsernamePasswordAuthenticationFilter.IS_CUSTOM_PARAMETER_KEY)) {
			log.info("========== Custom Style ==========");
			Map<String, List<String>> usernameMap = null;
			try {
				usernameMap = objectMapper.readValue(username, Map.class);
			} catch (Exception e) {
				log.error("", e);
				throw new UsernameNotFoundException("파라미터 정보를 변환하는데 실패하였습니다. username: " + username);
			}

			ezUserDetails 			= getEzUserDetails(usernameMap, rowMapper);
			tmpGrantedAuthorities 	= getGrantedAuthorities(usernameMap);
		} else {
			log.info("========== Default Style ==========");
			ezUserDetails 			= getEzUserDetails(username, rowMapper);
			tmpGrantedAuthorities 	= getGrantedAuthorities(username);
		}

		if (ezUserDetails == null) {
			log.error("{} 사용자 정보가 없거나 1개 초과일 수 있습니다.", username);
			throw new UsernameNotFoundException("사용자 정보가 에러");
		}


		// 인증된 사용자의 권한의 하위 권한 불러오기
		Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) roleHierarchy.getReachableGrantedAuthorities(tmpGrantedAuthorities);
		log.info("===== 인증된 권한 목록: {}", grantedAuthorities);

		if (StringUtils.isNotEmpty(ipCheckQuery) && ipCheckDomains != null && ipCheckDomains.length > 0) {
			Pattern urlPattern = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");
		    Matcher mc = urlPattern.matcher(request.getRequestURL());

		    String requestDomain = "";
			if (mc.matches() && mc.groupCount() > 2) {
				requestDomain = mc.group(2);
			}

			String reqIp = request.getRemoteAddr();
			log.info("IP 체크 대상 requestDomain {}, requestIP{}", requestDomain, reqIp);

			for (String domain : ipCheckDomains) {
				if (requestDomain.indexOf(domain+".") > -1) {
					boolean isContinue = false;
					if (ipList.contains(reqIp)) {
						isContinue = true;
					} else {
						for (String ip : ipList) {
							if (ip.indexOf("*") > -1) {
								String ipCut = ip.substring(0, ip.indexOf("*"));
				    			if (reqIp.startsWith(ipCut)) {
				    				isContinue = true;
				    				break;
				    			}
							}
						}
					}
					if (isContinue == false) {
						log.error("권한 허가 목록에 없는 IP정보입니다.");
						log.error("username: {}, reqIp: {}, reqUrl", username, reqIp, request.getRequestURL().toString());
						throw new UsernameNotFoundException("권한 허가 목록에 없는 IP 정보입니다.");
					} else {
						log.debug("권한 허가 PASS!!!");
					}
					break;
				}
			}
		}

        return new EzUserDetails(ezUserDetails.getUsername(),
        						 ezUserDetails.getPassword(),
        						 ezUserDetails.isEnabled(),
        						 ezUserDetails.isAccountNonExpired(),
        						 ezUserDetails.isCredentialsNonExpired(),
        						 ezUserDetails.isAccountNonLocked(),
        						 grantedAuthorities,
        						 ezUserDetails.getUser());
	}

	protected Set<GrantedAuthority> getGrantedAuthorities(String username) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		List<String> auths = this.jdbcTemplate.query(authoritiesQuery,
				new Object[] { username }, new RowMapper<String>() {
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

	protected Set<GrantedAuthority> getGrantedAuthorities(Map<String, List<String>> parameterMap) {
		// TODO: UsernamePasswordAuthenticationFilter 활용하여 로그인 파라미터 커스터 마이징 할 경우 재정의 하여 사용한다.
		return null;
	}

	protected EzUserDetails getEzUserDetails(String userId, RowMapper rowMapper) throws UsernameNotFoundException {
		EzUserDetails ezUserDetails = null;
		try {
			ezUserDetails = (EzUserDetails) this.jdbcTemplate.queryForObject(userQuery, new Object[] { userId }, rowMapper);
		} catch(Exception e) {
			log.error("{}", userId, e.toString());
		}
		return ezUserDetails;
	}

	protected EzUserDetails getEzUserDetails(Map<String, List<String>> parameterMap, RowMapper rowMapper) throws UsernameNotFoundException {
		// TODO: UsernamePasswordAuthenticationFilter 활용하여 로그인 파라미터 커스터 마이징 할 경우 재정의 하여 사용한다.
		return null;
	}

	protected String getParameter(String key, Map<String, List<String>> parameterMap) {
		List<String> value = parameterMap.get(key);
		return value==null?null:value.get(0);
	}

	protected String [] getParameterValues(String key, Map<String, List<String>> parameterMap) {
		List<String> value = parameterMap.get(key);
		//return value==null?null:value.stream().toArray(String[]::new);
		return value.toArray(new String[value.size()]);
	}
}
