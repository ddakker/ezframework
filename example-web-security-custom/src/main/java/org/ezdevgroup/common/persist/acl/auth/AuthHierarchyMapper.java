package org.ezdevgroup.common.persist.acl.auth;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.ezdevgroup.common.domain.entity.AuthHierarchy;
import org.ezdevgroup.common.service.acl.auth.dto.AuthHierarchyDto;


public interface AuthHierarchyMapper {

	@Select("/*"
			+ " 		권한에 대한 계층 정보"
			+ " 		getList"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthHierarchyMapper"
			+ " */"
			+ " SELECT AH.SEQ																"
			+ "			, AH.PARENT_AUTH_CD													"
			+ "         , (SELECT AUTH_NM FROM  ACL_AUTH WHERE AUTH_CD = AH.CHILD_AUTH_CD)	AS parentAuthNm"
			+ "         , AH.CHILD_AUTH_CD												   	"
			+ "         , (SELECT AUTH_NM FROM  ACL_AUTH WHERE AUTH_CD = AH.CHILD_AUTH_CD) 	AS childAuthNm"
			+ " FROM ACL_AUTH_HIERARCHY AH"
			+ " ORDER BY AH.PARENT_AUTH_CD")
	public List<AuthHierarchy> getList();

	@Select("/*"
			+ " 		권한에 대한 계층 정보 갯수(존재여부)"
			+ " 		getAuthHierarchyCnt"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthHierarchyMapper"
			+ " */"
			+ " SELECT COUNT(*)"
			+ " FROM ACL_AUTH_HIERARCHY"
			+ " WHERE PARENT_AUTH_CD = #{parentAuthCd}"
			+ " AND CHILD_AUTH_CD = #{childAuthCd}")
	public int getAuthHierarchyCnt(AuthHierarchyDto authHierarchyDto);

	@Insert("/*"
			+ " 		계층 정보 저장"
			+ " 		add"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthHierarchyMapper"
			+ " */"
			+ " INSERT "
			+ " INTO ACL_AUTH_HIERARCHY (PARENT_AUTH_CD, CHILD_AUTH_CD)VALUES"
			+ " (#{parentAuthCd}, #{childAuthCd})")
	public int add(AuthHierarchyDto authHierarchyDto);

	@Delete("/*"
			+ " 		계층 정보 삭제"
			+ " 		remove"
			+ " 		org.ezdevgroup.common.service.acl.auth.mapper.AuthHierarchyMapper"
			+ " */"
			+ " DELETE FROM ACL_AUTH_HIERARCHY WHERE SEQ = #{seq} ")
	public int remove(Integer seq);
}
