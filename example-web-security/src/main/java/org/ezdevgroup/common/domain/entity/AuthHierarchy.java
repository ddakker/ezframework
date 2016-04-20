package org.ezdevgroup.common.domain.entity;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

/**
 * 권한에 대한 계층 정보
 * 		- ACL_AUTH_HIERARCHY
 * @author ddakker 2014. 9. 01.
 */
@Getter @Setter
public class AuthHierarchy extends PagingVo {
	private Long seq;
	private String parentAuthCd;
	private String childAuthCd;
	
	// -------------------- UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
	// -------------------- //UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
}
