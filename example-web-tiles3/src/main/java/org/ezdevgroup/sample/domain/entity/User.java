package org.ezdevgroup.sample.domain.entity;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends PagingVo {
	private String userId;
	private Integer userType;
    private String userNm;
    private String useYn;
}
