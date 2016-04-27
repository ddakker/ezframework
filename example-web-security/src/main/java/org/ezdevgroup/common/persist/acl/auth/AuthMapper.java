package org.ezdevgroup.common.persist.acl.auth;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ezdevgroup.common.domain.entity.Auth;
import org.ezdevgroup.common.domain.entity.AuthHierarchy;
import org.ezdevgroup.common.service.acl.auth.dto.AuthDto;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;

public interface AuthMapper {
	public List<Auth> getList(AuthDto authDto);
	public Auth getView(String authCd);

	@Insert("/*"
			+ " 		권한 등록"
			+ " 		add"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthMapper"
			+ " */"
			+ " INSERT INTO "
			+ " ACL_AUTH "
			+ " VALUES(#{authCd}, #{authNm}, #{authDc}, TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'), TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'))")
	public int add(AuthDto auth);

	@Update("/*"
			+ " 		권한 수정"
			+ " 		modify"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthMapper"
			+ " */"
			+ " UPDATE ACL_AUTH SET"
			+ " 	AUTH_NM = #{authNm}"
			+ " 	, AUTH_DC = #{authDc}"
			+ " 	, MODI_DT = TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS')"
			+ " WHERE AUTH_CD = #{authCd}")
	public int modify(AuthDto auth);

	@Delete("/*"
			+ " 		권한 삭제"
			+ " 		remove"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthMapper"
			+ " */"
			+ " DELETE FROM ACL_AUTH"
			+ " WHERE AUTH_CD = #{authCd}")
	public int remove(String authCd);

	@Select("/* "
			+ " 		권한에 대한 리소스 등록 전 등록여부 조회 "
			+ " 		removeAuthResource "
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthMapper "
			+ " */"
			+ " SELECT COUNT(*) "
			+ " FROM ACL_AUTH_RESOURCE "
			+ " WHERE AUTH_CD = #{authCd} "
			+ " AND RESOURCE_CD = #{resourceCd}")
	public int getAuthResourceCnt(ResourceDto resourceDto);

	@Insert("/* "
			+ " 		권한에 대한 리소스 등록 "
			+ " 		removeAuthResource "
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthMapper "
			+ " */"
			+ " INSERT "
			+ " INTO ACL_AUTH_RESOURCE "
			+ " VALUES(#{authCd}, #{resourceCd}, TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'), TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'))")
	public int addAuthResource(ResourceDto resourceDto);

	public int removeAuthResource(ResourceDto resourceDto);

	public List<AuthHierarchy> getAuthHierarchyList();
}
