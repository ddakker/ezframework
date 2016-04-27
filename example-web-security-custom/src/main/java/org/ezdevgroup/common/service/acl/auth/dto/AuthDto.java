package org.ezdevgroup.common.service.acl.auth.dto;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthDto extends PagingVo {
	private String authCd;			// 권한코드
	private String authNm;			// 권한명
	private String authDc;			// 권한설명
	
	private String [] authCds;
	private String [] authNms;
	private String [] authDcs;
	private String [] regDts;
	private String [] modiDts;

	private String searchKey;		// 검색어키
	private String searchKwd;		// 검색어
}
