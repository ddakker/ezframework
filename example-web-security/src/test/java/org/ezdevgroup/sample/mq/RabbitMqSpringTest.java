package org.ezdevgroup.sample.mq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/spring/context-*.xml" })
public class RabbitMqSpringTest {
	private static Logger log = LoggerFactory.getLogger(RabbitMqSpringTest.class);
	
	@Resource
	RabbitTemplate amqpTemplate;
	
	@Test
	public void test_sender() {
		try {
			String queueName = "sms";
			String msg = "내용이다~~";
			amqpTemplate.convertAndSend(queueName, msg);
		} catch (Exception e) {
			log.error("e: {}",e.toString());
		}
	}
}
