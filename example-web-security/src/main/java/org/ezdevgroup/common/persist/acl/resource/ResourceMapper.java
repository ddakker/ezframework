package org.ezdevgroup.common.persist.acl.resource;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.ezdevgroup.common.domain.entity.Resource;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;


public interface ResourceMapper {

	public List<Resource> getList(ResourceDto resource);
	public int getListCnt(ResourceDto resource);

	@Insert("/*"
			+ " 	리소스 등록"
			+ " 	add"
			+ " 	org.ezdevgroup.common.service.acl.resource.mapper.ResourceMapper"
			+ " */"
			+ " INSERT "
			+ " INTO ACL_RESOURCE "
			+ " VALUES(#{resourceCd}, #{resourceNm}, #{resourceDc}, #{resourceUrl}, TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'), TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS'))")
	public int add(ResourceDto resource);

	@Update("/*"
			+ " 	리소스 수정"
			+ " 	modify"
			+ " 	org.ezdevgroup.common.service.acl.resource.mapper.ResourceMapper"
			+ " */"
			+ " UPDATE ACL_RESOURCE SET"
			+ " 	RESOURCE_NM = #{resourceNm}"
			+ " 	, RESOURCE_URL = #{resourceUrl}"
			+ " 	, RESOURCE_DC = #{resourceDc}"
			+ " 	, MODI_DT = TO_CHAR(current_timestamp, 'YYYYMMDDHH24MISS')"
			+ " WHERE RESOURCE_CD = #{resourceCd}")
	public int modify(ResourceDto resource);

	@Delete("/*"
			+ " 	리소스 삭제"
			+ " 	remove"
			+ " 	org.ezdevgroup.common.service.acl.resource.mapper.ResourceMapper"
			+ " */"
			+ " DELETE FROM ACL_RESOURCE"
			+ " WHERE RESOURCE_CD = #{resourceCd}")
	public int remove(String resourceCd);
}