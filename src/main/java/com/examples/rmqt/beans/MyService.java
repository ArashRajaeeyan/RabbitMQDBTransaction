package com.examples.rmqt.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.examples.rmqt.QueueConfig;

@Component
public class MyService {
	@Autowired
	DAO dao;

	@Autowired
	QSender qSender;

	@SuppressWarnings("serial")
	@Transactional
	public int operationQueueFirst(boolean failBetween, boolean failAfter, int id, String name) {
		qSender.sendMsg(QueueConfig.ROUTING_KEY_1, id + " " + name);
		
		if (failBetween) {
			throw new DataAccessException("fake exception after db") {
			};
		}

		int res = dao.write(id, name);
		if (failAfter) {
			throw new DataAccessException("fake exception after queue, db") {
			};
		}
		
		System.out.println("---> service operation done");
		return res;
	}
	
	@SuppressWarnings("serial")
	@Transactional
	public int operationDBFirst(boolean failBetween, boolean failAfter, int id, String name) {
		int res = dao.write(id, name);
		
		if (failBetween) {
			throw new DataAccessException("fake exception after db") {
			};
		}		
		qSender.sendMsg(QueueConfig.ROUTING_KEY_1, id + " " + name);
		
		if (failAfter) {
			throw new DataAccessException("fake exception after db, queue") {
			};
		}		
		System.out.println("---> service operation done");
		return res;
	}
}