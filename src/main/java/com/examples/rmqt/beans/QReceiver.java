package com.examples.rmqt.beans;

import org.springframework.stereotype.Component;

@Component
public class QReceiver {

	private String message;

	public QReceiver() {
		System.out.println("---> QReceiver created");
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void receiveMessage(String message) {
		this.message = message;
		System.out.println("---> Received <" + message + ">");
	}

}
