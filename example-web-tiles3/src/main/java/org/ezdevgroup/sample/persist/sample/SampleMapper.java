package org.ezdevgroup.sample.persist.sample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ezdevgroup.sample.domain.EzMap;
import org.ezdevgroup.sample.domain.entity.TestVo;
import org.ezdevgroup.sample.service.sample.dto.SampleDto;

public interface SampleMapper {
	public List<EzMap> getSampleOneToOne();

	public EzMap getSample(Map<String, String> params);

	@Select("SELECT "
			+ "		SEQ"
			+ "		, NAME"
			+ "		, EMAIL"
			+ "		, AB_CD"
			+ "	FROM EZ_SAMPLE WHERE EMAIL = #{sampleDto.email} "
			+ "	ORDER BY SEQ DESC")
	public List<EzMap> getSampleListJava(@Param("sampleDto") SampleDto sampleDto);

	@Select("SELECT COUNT(*) "
			+ "	FROM EZ_SAMPLE "
			+ " WHERE NAME = #{name} "
			+ " AND EMAIL = #{email} ")
	public int getSampleListSize(Map<String, Object> params);


	public List<EzMap> getSampleListXml(SampleDto sampleDto);

	public List<TestVo> getSampleListVo(SampleDto sampleDto);

	public List<Map<String, Object>> getSampleListHashMap(SampleDto sampleDto);

	public int getSampleCnt(SampleDto sampleDto);


	@Select("SELECT "
			+ "		SEQ"
			+ "		, MSG"
			+ "	FROM EZ_SAMPLE_DESC WHERE SEQ = #{seq} "
			+ "	ORDER BY SEQ DESC")
	public List<EzMap> getSampleDesc(@Param("seq") Integer seq);



	public int addSample(SampleDto sampleDto);
	public int addSample(Map<String, Object> params);
	public int addSampleWithSelectKey(SampleDto sampleDto);
	public int modifySample(SampleDto sampleDto);

	@Delete("DELETE FROM EZ_SAMPLE WHERE SEQ = #{seq}")
	public int deleteSample(Map<String, String> params);
}