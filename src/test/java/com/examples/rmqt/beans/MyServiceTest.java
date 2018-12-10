package com.examples.rmqt.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.examples.rmqt.QueueConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { QueueConfig.class })
public class MyServiceTest {
	@Autowired
	MyService service;

	@Autowired
	DAO dao;

	@Autowired
	QReceiver reciever;

	@Test
	public void testHappyPath() {
		int id = 101;
		String name = "Arash";
		String msg = id + " " + name;
		boolean failBetween = false;
		boolean failAfter = false;
		int r = -1;
		reciever.setMessage(null);
		// happy path
		r = service.operationQueueFirst(failBetween, failAfter, id, name);

		assertEquals(r, 1);
		Student s = dao.find(id);
		assertEquals(id, s.getId());
		wait(5); // wait for message to be received

		assertEquals(msg, reciever.getMessage());
		System.out.println("---> Message Recieved from queueue!");
	}

	@Test
	public void testFailAfterBoth() {
		int id = 102;
		String name = "Arash";
		boolean failBetween = false;
		boolean failAfter = true;
		int r = -1;
		reciever.setMessage(null);
		// happy path
		try {
			r = service.operationQueueFirst(failBetween, failAfter, id, name);
			fail("Should through exception and rollback");
		} catch (DataAccessException ex) {

		}

		assertEquals(r, -1);
		Student s = dao.find(id);
		assertNull(s);
		wait(5); // wait for message to be received

		assertNull("Should be null but value is " + reciever.getMessage(), reciever.getMessage());
		System.out.println("---> Message Recieved from queueue!");
	}

	@Test
	public void testAfterQueueBeforeDB() {
		int id = 102;
		String name = "Arash";
		boolean failBetween = true;
		boolean failAfter = false;
		int r = -1;
		reciever.setMessage(null);
		// happy path
		try {
			r = service.operationQueueFirst(failBetween, failAfter, id, name);
			fail("Should through exception and rollback");
		} catch (DataAccessException ex) {

		}

		assertEquals(r, -1);
		Student s = dao.find(id);
		assertNull(s);
		wait(5); // wait for message to be received

		assertNull("Should be null but value is " + reciever.getMessage(), reciever.getMessage());
		System.out.println("---> Message Recieved from queueue!");
	}

	@Test
	public void testAfterDBBeforeQueue() {
		int id = 102;
		String name = "Arash";
		boolean failBetween = true;
		boolean failAfter = false;
		int r = -1;
		reciever.setMessage(null);
		// happy path
		try {
			r = service.operationDBFirst(failBetween, failAfter, id, name);
			fail("Should through exception and rollback");
		} catch (DataAccessException ex) {

		}

		assertEquals(r, -1);
		Student s = dao.find(id);
		assertNull(s);
		wait(5); // wait for message to be received

		assertNull("Should be null but value is " + reciever.getMessage(), reciever.getMessage());
		System.out.println("---> Message Recieved from queueue!");
	}

	private void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
