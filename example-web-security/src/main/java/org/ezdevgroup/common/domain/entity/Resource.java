package org.ezdevgroup.common.domain.entity;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

/**
 * URL 패턴 정보
 * 		- ACL_RESOURCE
 * @author ddakker 2014. 8. 29.
 */
@Getter @Setter
public class Resource extends PagingVo {
	private String resourceCd;			// 롤코드
	private String resourceNm;			// 명칭
	private String resourceDc;			// 설명
	private String resourceUrl;			// URL 패턴
	private String regDt;				// 등록일
	private String modiDt;				// 수정일
	
	// -------------------- UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
	private String authResourceYn;
	// -------------------- //UI 구성 및 JOIN 확장 TABLE FIELD ----------------------
}
