/**
 * Create On: Dec 20, 2015 8:52:06 PM
 * @author mohamed265
 */
package com.basicemail.entity;

import java.sql.Timestamp;

/**
 * @author mohamed265
 *
 */
public class Message {

	private int id;

	private int senderId;

	private int threadMsgId;

	private String subject;

	private String body;

	private String attachment;

	private Timestamp timestap;

	public Message() {
	}

	public Message(int id, int senderId, int threadMsgId, String subject, String body, String attachment,
			Timestamp timestap) {
		this.id = id;
		this.senderId = senderId;
		this.threadMsgId = threadMsgId;
		this.subject = subject;
		this.body = body;
		this.attachment = attachment;
		this.timestap = timestap;
	}

	public Message(int id, int senderId, int threadMsgId, String subject, String body, Timestamp timestap) {
		this.id = id;
		this.senderId = senderId;
		this.threadMsgId = threadMsgId;
		this.subject = subject;
		this.body = body;
		this.timestap = timestap;
	}

	public int getId() {
		return id;
	}

	public int getSenderId() {
		return senderId;
	}

	public int getThreadMsgId() {
		return threadMsgId;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getAttachment() {
		return attachment;
	}

	public Timestamp getTimestap() {
		return timestap;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public void setThreadMsgId(int threadMsgId) {
		this.threadMsgId = threadMsgId;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public void setTimestap(Timestamp timestap) {
		this.timestap = timestap;
	}
}
