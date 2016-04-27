package org.ezdevgroup.sample.domain.entity;

import org.ezdevgroup.ezframework.web.vo.Vo;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TestVo extends Vo {
	String seq;
	String name;
	String email;
	String abCd;
}
