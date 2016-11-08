package org.ezdevgroup.sample.service.log;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.ezdevgroup.sample.domain.EzMap;
import org.ezdevgroup.sample.persist.log.LogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogAnnoService {
	@Resource LogMapper logMapper;

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void log(String msg) {
		logMapper.log(msg);
	}

	public List<EzMap> getLogs() {
		return logMapper.getLogs();
	}

	public int getLogCnt(@Param("msg") String msg) {
		return logMapper.getLogCnt(msg);
	}
}
