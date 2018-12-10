package com.examples.rmqt.beans;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.examples.rmqt.QueueConfig;

@Component
public class QSender {
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@PostConstruct
	public void init() {
		rabbitTemplate.setChannelTransacted(true);
	}
	
	// commented Transaction as by default it should propagate from parent
	//@Transactional(propagation=Propagation.REQUIRED)
	public void sendMsg(String key, String msg) {
		rabbitTemplate.convertAndSend(QueueConfig.EXCHANGE_DIRECT_NAME, key, msg);
		System.out.println("---> message " + msg + " send with key " + key);
	}
}
