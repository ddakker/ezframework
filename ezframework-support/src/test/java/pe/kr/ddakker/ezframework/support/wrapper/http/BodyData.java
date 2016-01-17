package pe.kr.ddakker.ezframework.support.wrapper.http;

import lombok.Getter;
import lombok.Setter;
import pe.kr.ddakker.ezframework.web.vo.Vo;

@Setter @Getter
public class BodyData extends Vo {
	private int age;
	private String name;
}
