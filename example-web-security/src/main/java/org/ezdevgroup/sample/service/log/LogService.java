package org.ezdevgroup.sample.service.log;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ezdevgroup.sample.domain.EzMap;
import org.ezdevgroup.sample.persist.log.LogMapper;
import org.springframework.stereotype.Service;

@Service
public class LogService {
	@Resource LogMapper logMapper;

	public void log(String msg) {
		logMapper.log(msg);
	}

	public List<EzMap> getLogs() {
		return logMapper.getLogs();
	}
}
