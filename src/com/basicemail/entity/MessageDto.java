package com.basicemail.entity;

public class MessageDto {

	private int threadID;

	private int threadMessagesNumber;

	private User sender;

	private Message message;

	private boolean is_readed;

	public MessageDto() {

	}

	public MessageDto(int threadID, User sender, Message message, int threadMessagesNumber, boolean is_readed) {
		super();
		this.threadID = threadID;
		this.sender = sender;
		this.message = message;
		this.threadMessagesNumber = threadMessagesNumber;
		this.is_readed = is_readed;
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public int getThreadMessagesNumber() {
		return threadMessagesNumber;
	}

	public void setThreadMessagesNumber(int threadMessagesNumber) {
		this.threadMessagesNumber = threadMessagesNumber;
	}

	public boolean isIs_readed() {
		return is_readed;
	}

	public void setIs_readed(boolean is_readed) {
		this.is_readed = is_readed;
	}
}
