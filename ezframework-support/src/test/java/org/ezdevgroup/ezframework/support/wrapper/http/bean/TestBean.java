package org.ezdevgroup.ezframework.support.wrapper.http.bean;

import java.util.List;
import java.util.Map;

import org.ezdevgroup.ezframework.web.vo.DataVo;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class TestBean extends DataVo {
	String pathVar1;
	String param1;
	String testParam1;
	List<Map> listMap;
}
