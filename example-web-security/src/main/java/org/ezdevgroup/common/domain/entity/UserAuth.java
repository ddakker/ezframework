package org.ezdevgroup.common.domain.entity;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

/**
 * 사용자정보
 * 		- EZ_USER
 * @auther ddakker 2014. 7. 7.
 */
@Getter @Setter
public class UserAuth extends PagingVo {
	private String userAuthKey;
	private String authCd;
	private String regDt;
	private String modiDt;
	
	// -------------------- UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
	private String userId;
	private Integer userType;
    private String userNm;    
	private String authNm;
	// -------------------- //UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
}
