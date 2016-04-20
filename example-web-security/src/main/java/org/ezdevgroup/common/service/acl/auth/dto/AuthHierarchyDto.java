package org.ezdevgroup.common.service.acl.auth.dto;

import org.ezdevgroup.ezframework.web.vo.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthHierarchyDto extends Vo {
	private Long seq;
	private String parentAuthCd;
	private String childAuthCd;
}
