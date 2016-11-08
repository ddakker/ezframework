package org.ezdevgroup.sample.service.sample;

import java.util.Map;

import javax.annotation.Resource;

import org.ezdevgroup.sample.persist.sample.SampleMapper;
import org.ezdevgroup.sample.service.log.LogAnnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleAnnoService {
	private static Logger log = LoggerFactory.getLogger(SampleAnnoService.class);

	@Resource SampleMapper 	sampleMapper;
	@Resource LogAnnoService 	logAnoService;

	@Transactional
	public int addSample(Map<String, Object> params) {
		return sampleMapper.addSample(params);
	}

	public int getSampleListSize(Map<String, Object> params) {
		return sampleMapper.getSampleListSize(params);
	}

	@Transactional
	public void serviceTranTest(Map<String, Object> params) {
		logAnoService.log((String) params.get("email"));

		int addCnt = this.addSample(params);
		log.debug("addCnt: {}", addCnt);

		logAnoService.log((String) params.get("email"));

		if( true ){
			throw new RuntimeException("강제 에러");
		}
	}
}
