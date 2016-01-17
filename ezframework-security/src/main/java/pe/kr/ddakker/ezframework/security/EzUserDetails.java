package pe.kr.ddakker.ezframework.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;

/**
 * 인증 정보
 * @author ddakker 2014. 8. 28.
 *
 */
public class EzUserDetails extends org.springframework.security.core.userdetails.User {
	private Object user;

	public EzUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Object user) throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired,  credentialsNonExpired, accountNonLocked, new HashSet<GrantedAuthority>());
		this.user = user;
	}

	public EzUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> grantedAuthorities, Object user) throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired,  credentialsNonExpired, accountNonLocked, grantedAuthorities);
		this.user = user;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

}
