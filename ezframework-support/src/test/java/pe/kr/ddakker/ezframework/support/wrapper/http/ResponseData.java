package pe.kr.ddakker.ezframework.support.wrapper.http;

import lombok.Getter;
import lombok.Setter;
import pe.kr.ddakker.ezframework.web.vo.DataVo;

@Setter @Getter
public class ResponseData extends DataVo {
	private ResultSampleBody sampleBodyVo;

}
