package org.ezdevgroup.sample.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ezdevgroup.sample.service.log.LogAnnoService;
import org.ezdevgroup.sample.service.log.LogService;
import org.ezdevgroup.sample.service.sample.SampleAnnoService;
import org.ezdevgroup.sample.service.sample.SampleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:config/spring/context-common.xml",
		"classpath*:config/spring/context-datasource.xml",
		"classpath*:config/spring/context-transaction.xml"
})
public class TransactionTestCase {
	@Resource SampleService sampleService;
	@Resource LogService logService;

	@Resource SampleAnnoService sampleAnnoService;
	@Resource LogAnnoService logAnnoService;

	@Before
	public void test_before() {

	}

	@Test
    public void test_선언적_트랜잭션() {
		assertThat(sampleService, notNullValue());

		long time = System.currentTimeMillis();
		String name = "ddakker" + time;
		String email = name + "@gmail.com";

		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("email", email);

		try {
			sampleService.serviceTranTest(params);
		} catch (Exception e) {
		}


		assertThat("롤백 되야 함", sampleService.getSampleListSize(params), is(0));
		assertThat("롤백 안 되어야 함", logService.getLogCnt(email), is(2));
	}

}
