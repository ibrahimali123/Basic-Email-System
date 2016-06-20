/**
 * Create On: Dec 20, 2015 9:02:01 PM
 * @author mohamed265
 */
package com.basicemail.entity;

/**
 * @author mohamed265
 *
 */
public class RecipientMessage {
	private int id;

	private int reciverId;

	private int threadMsgId;

	private int mesId;

	public RecipientMessage() {
	}

	public RecipientMessage(int id, int reciverId, int threadMsgId, int mesId) {
		this.id = id;
		this.reciverId = reciverId;
		this.threadMsgId = threadMsgId;
		this.mesId = mesId;
	}

	public int getId() {
		return id;
	}

	public int getReciverId() {
		return reciverId;
	}

	public int getThreadMsgId() {
		return threadMsgId;
	}

	public int getMesId() {
		return mesId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setReciverId(int reciverId) {
		this.reciverId = reciverId;
	}

	public void setThreadMsgId(int threadMsgId) {
		this.threadMsgId = threadMsgId;
	}

	public void setMesId(int mesId) {
		this.mesId = mesId;
	}
}
