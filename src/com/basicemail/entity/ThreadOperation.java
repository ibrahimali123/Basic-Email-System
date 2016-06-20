package com.basicemail.entity;

public class ThreadOperation {

	private int id;

	private int userID;

	private int threadID;

	private boolean isDeleted;

	private boolean isReaded;

	private boolean isTrashed;

	private boolean isArchived;

	public ThreadOperation() {
	}

	public ThreadOperation(int id, int userID, int threadID, boolean isDeleted, boolean isReaded, boolean isTrashed,
			boolean isArchived) {
		super();
		this.id = id;
		this.userID = userID;
		this.threadID = threadID;
		this.isDeleted = isDeleted;
		this.isReaded = isReaded;
		this.isTrashed = isTrashed;
		this.isArchived = isArchived;
	}

	public int getId() {
		return id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public boolean isReaded() {
		return isReaded;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}
}
