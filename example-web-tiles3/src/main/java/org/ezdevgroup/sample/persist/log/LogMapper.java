package org.ezdevgroup.sample.persist.log;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ezdevgroup.sample.domain.EzMap;


public interface LogMapper {
	@Insert("INSERT INTO EZ_LOG (msg)VALUES(#{msg})")
	public void log(@Param("msg") String msg);

	@Select("SELECT SEQ, MSG, REG_DT FROM EZ_LOG ORDER BY SEQ DESC")
	public List<EzMap> getLogs();

	@Select("SELECT COUNT(*) FROM EZ_LOG WHERE msg = #{msg}")
	public int getLogCnt(@Param("msg") String msg);
}