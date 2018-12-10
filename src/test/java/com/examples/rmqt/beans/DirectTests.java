package com.examples.rmqt.beans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.examples.rmqt.QueueConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { QueueConfig.class })
public class DirectTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	DirectExchange directExchange;

	@Test
	public void test() {
		rabbitTemplate.setChannelTransacted(true);
		Object msg = "DirectMSG";
		rabbitTemplate.convertAndSend(QueueConfig.EXCHANGE_DIRECT_NAME, QueueConfig.ROUTING_KEY_1, msg);
	}

}
