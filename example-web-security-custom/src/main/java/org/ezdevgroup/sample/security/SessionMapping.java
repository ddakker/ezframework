package org.ezdevgroup.sample.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ezdevgroup.sample.domain.entity.User;
import org.springframework.jdbc.core.RowMapper;

import org.ezdevgroup.ezframework.security.EzUserDetails;

public class SessionMapping implements RowMapper {

	/**
	 * 인증 정보보가 넘어온다.
	 * @param rs
	 * @param rownum
	 * @return
	 * @auther ddakker 2014. 6. 12.
	 */
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		String	userId					= rs.getString("USER_ID");
		String	userNm					= rs.getString("USER_NM");
		Integer	userType				= rs.getInt("USER_TYPE");
		String	userPwd					= rs.getString("USER_PWD");
		String	useYn					= rs.getString("USE_YN");
        boolean enabled  				= rs.getBoolean("ENABLED");
        boolean accountNonExpired		= true;
        boolean credentialsNonExpired	= true;
        boolean accountNonLocked		= true;


        // 세션 항목 설정
        User user = new User();
        user.setUserId(userId);
        user.setUserNm(userNm);
        user.setUserType(userType);
        user.setUseYn(useYn);

        return new EzUserDetails(userId, userPwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, user);
    }
}
