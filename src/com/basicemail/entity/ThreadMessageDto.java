package com.basicemail.entity;

public class ThreadMessageDto {

	private int threadID;

	private String receiver;

	private String sender;

	private Message message;

	public ThreadMessageDto() {

	}

	public ThreadMessageDto(int threadID, String receiver, String sender, Message message) {
		super();
		this.threadID = threadID;
		this.receiver = receiver;
		this.sender = sender;
		this.message = message;
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
