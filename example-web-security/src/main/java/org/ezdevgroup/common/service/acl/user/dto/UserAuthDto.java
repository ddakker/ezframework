package org.ezdevgroup.common.service.acl.user.dto;

import org.ezdevgroup.ezframework.web.vo.PagingVo;
import org.ezdevgroup.ezframework.web.vo.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAuthDto extends PagingVo {
	private String userId;
	private String authCd;
	private String userAuthKey;

	private String[] userIdArr;
	private String[] authCdArr;
	private String[] userAuthKeyArr;

	private String searchKey;		// 검색어키
	private String searchKwd;		// 검색어
}
