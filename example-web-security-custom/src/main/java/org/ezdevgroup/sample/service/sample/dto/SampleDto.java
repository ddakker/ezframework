package org.ezdevgroup.sample.service.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.ezdevgroup.ezframework.web.vo.PagingVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SampleDto extends PagingVo {
	private Integer seq;		// 순번
	private String 	name;		// 이름
	private String 	email;		// 이메일
	
	//@Size(min=0, max=20)
	@NotNull
	private String test;
}
