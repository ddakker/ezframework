package org.ezdevgroup.common.domain.entity;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

/**
 * 권한 정보
 * 	-	 ACL_AUTH
 * @author ddakker 2014. 9. 01.
 */
@Getter @Setter
public class Auth extends PagingVo {
	private String authCd;			// 권한코드
	private String authNm;			// 권한명
	private String authDc;			// 권한설명
	private String regDt;			// 등록일
	private String modiDt;			// 수정일
	
	// -------------------- UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
	// -------------------- //UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
}
