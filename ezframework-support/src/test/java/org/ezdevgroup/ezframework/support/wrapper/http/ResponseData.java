package org.ezdevgroup.ezframework.support.wrapper.http;

import org.ezdevgroup.ezframework.web.vo.DataVo;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ResponseData extends DataVo {
	private ResultSampleBody sampleBodyVo;

}
