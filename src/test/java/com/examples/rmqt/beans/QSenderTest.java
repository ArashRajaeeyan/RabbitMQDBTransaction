package com.examples.rmqt.beans;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.examples.rmqt.QueueConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { QueueConfig.class })
public class QSenderTest {

	@Autowired
	QSender sender;
	
	@Autowired
	QReceiver reciever;
	
	@Test
	public void test() {
		String MSG = "QSenderTest:test";
		sender.sendMsg(QueueConfig.ROUTING_KEY_1, MSG);
		System.out.println("Message Send, will wait");
		
		wait(10); // wait for message to be received
		assertEquals(MSG, reciever.getMessage());		
		System.out.println("Message Recieved from other side!");

		wait(5); // wait so we can see result before scrolling down
	}
	
	private void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

}
