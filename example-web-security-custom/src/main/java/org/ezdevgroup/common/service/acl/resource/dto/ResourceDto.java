package org.ezdevgroup.common.service.acl.resource.dto;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResourceDto extends PagingVo {
	private String resourceCd;				// 리소스코드
	private String resourceNm;			// 명칭
	private String resourceDc;			// 설명
	private String resourceUrl;			// URL 패턴
	
	private String authCd;					// AuthVo.authCd
	private String authResourceYn;			// ResourceVo.authResourceYn

	private String [] resourceCds;
	private String [] resourceNms;
	private String [] resourceDcs;
	private String [] resourceUrls;
	private String [] authResourceYns;		// 권한에 따른 리소스 사용유무
	private String searchKey;				// 검색키
	private String searchKwd;				// 검색어
}
