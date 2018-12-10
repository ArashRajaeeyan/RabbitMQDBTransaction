package com.examples.rmqt;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.examples.rmqt.beans.QReceiver;

@Configuration
@ComponentScan(basePackages = { "com.examples.rmqt" })
public class QueueConfig {
	public static final String Q1_NAME = "testq-1";
	public static final String Q2_NAME = "testq-2";
	public static final String ROUTING_KEY_1 = "key1";
	public static final String ROUTING_KEY_2 = "key2";
	public static final String EXCHANGE_DIRECT_NAME = "ArashDirectExchange";

	public static final String topicExchangeName = "topic1";

	@Bean
	Queue queue() {
		return new Queue(Q1_NAME, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_DIRECT_NAME);
	}

	/**
	 * This method creates binding beween queue and exchange when project is initilized
	 * @param queue to be bounded
	 * @param exchange this is binding the queues to direct exchange you can change the method to bind it to topic or other type of exchange
	 * @return
	 */
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_1);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Q1_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(QReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
