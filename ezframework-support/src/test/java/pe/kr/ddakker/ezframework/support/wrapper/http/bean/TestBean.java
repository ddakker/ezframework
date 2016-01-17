package pe.kr.ddakker.ezframework.support.wrapper.http.bean;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import pe.kr.ddakker.ezframework.web.vo.DataVo;


@Getter @Setter
public class TestBean extends DataVo {
	String pathVar1;
	String param1;
	String testParam1;
	List<Map> listMap;
}
