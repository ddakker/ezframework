package org.ezdevgroup.common.persist.acl.user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ezdevgroup.common.domain.entity.UserAuth;
import org.ezdevgroup.common.service.acl.user.dto.UserAuthDto;




public interface UserAuthMapper {
	public List<UserAuth> getList(UserAuthDto userAuthDto);
	public int getListCnt(UserAuthDto UserAuthDto);

	@Insert("/*"
			+ " 		사용자 권한 등록"
			+ " 		insertUserAuth"
			+ " 		org.ezdevgroup.sample.service.user.mapper.UserMapper"
			+ " */"
			+ " INSERT INTO "
			+ " ACL_USER_AUTH (USER_AUTH_KEY, AUTH_CD, REG_DT, MODI_DT) VALUES "
			+ " (#{userAuthKey}, #{authCd }, TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'), TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'))")
	public int insertUserAuth(UserAuthDto UserAuthDto);

	@Update("/*"
			+ " 		사용자 권한 수정"
			+ " 		modiryUserAuth"
			+ " 		org.ezdevgroup.sample.service.user.mapper.UserMapper"
			+ " */"
			+ " UPDATE ACL_USER_AUTH SET AUTH_CD = #{authCd}, MODI_DT = TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS')"
			+ " WHERE USER_AUTH_KEY = #{userAuthKey}")
	public int modiryUserAuth(UserAuthDto UserAuthDto);

	@Select("SELECT COUNT(*) FROM ACL_USER_AUTH WHERE USER_AUTH_KEY = #{userAuthKey}")
	public int getUserAuthCnt(@Param("userAuthKey") String userAuthKey);
}
