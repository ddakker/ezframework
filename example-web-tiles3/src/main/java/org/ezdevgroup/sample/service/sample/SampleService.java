package org.ezdevgroup.sample.service.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ezdevgroup.sample.domain.EzMap;
import org.ezdevgroup.sample.domain.entity.TestVo;
import org.ezdevgroup.sample.persist.sample.SampleMapper;
import org.ezdevgroup.sample.service.log.LogService;
import org.ezdevgroup.sample.service.sample.dto.SampleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.ezdevgroup.ezframework.web.vo.Paging;

@Service
public class SampleService {
	private static Logger log = LoggerFactory.getLogger(SampleService.class);

	@Resource SampleMapper 	sampleMapper;
	@Resource LogService 	logService;

	public List<EzMap> getSampleOneToOne() {
		return sampleMapper.getSampleOneToOne();
	}

	public EzMap getSample(Map<String, String> params) {
		return sampleMapper.getSample(params);
	}
	
	public EzMap getSample(Integer seq) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("seq", String.valueOf(seq));
		return sampleMapper.getSample(params);
	}
	
	public List<EzMap> getSampleListJava(SampleDto sampleDto) {
		return sampleMapper.getSampleListJava(sampleDto);
	}

	public List<EzMap> getSampleListXml(SampleDto sampleDto) {
		return sampleMapper.getSampleListXml(sampleDto);
	}
	
	public List<TestVo> getSampleListVo(SampleDto sampleDto) {
		return sampleMapper.getSampleListVo(sampleDto);
	}
	
	public List<Map<String, Object>> getSampleListHashMap(SampleDto sampleDto) {
		return sampleMapper.getSampleListHashMap(sampleDto);
	}
	
	public Paging<EzMap> getSampleList(SampleDto sampleDto) {
		Paging<EzMap> paging = new Paging<EzMap>();

		paging.setList(sampleMapper.getSampleListXml(sampleDto));
		paging.setTotalCount(sampleMapper.getSampleCnt(sampleDto));
		paging.setPaging(sampleDto);

		return paging;
	}
	
	public List<EzMap> getSampleDesc(Integer seq) {
		return sampleMapper.getSampleDesc(seq);
	}

	public int modifySample(SampleDto sampleDto) {
		return sampleMapper.modifySample(sampleDto);
	}
	
	public int addSample(SampleDto sampleDto) {
		int changeCnt = sampleMapper.addSampleWithSelectKey(sampleDto);
		return changeCnt;
	}
	
	public int serviceTranCommitTest(SampleDto sampleDto) {
		logService.log("[Service] 함수 이전" + sampleDto.getName());
		int changeCnt = sampleMapper.addSample(sampleDto);
		logService.log("[Service] 함수 이후" + sampleDto.getName());
		return changeCnt;
	}


	/**
	 * PROPAGATION_REQUIRED 트랜잭션 롤백 테스트
	 * @param sample
	 * @auther ddakker 2014. 5. 28.
	 */
	public void serviceTranRollbackTest(SampleDto sampleDto) {
		String name = sampleDto.getName();

		logService.log("[Service] 함수 이전 " + name);

		sampleDto.setName(name + "_before");
		log.debug("sql insert before exex cnt: {}", sampleMapper.addSample(sampleDto));

		sampleDto.setName(name + "_after");
		log.debug("sql insert after exex cnt: {}", sampleMapper.addSample(sampleDto));


		logService.log("[Service] 함수 이후" + name);

		if( true ){
			throw new RuntimeException("강제 에러");
		}
	}

}
