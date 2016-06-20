package com.basicemail.service;

import java.sql.SQLException;
import java.util.List;

import com.basicemail.dao.ComposeMessageDao;
import com.basicemail.entity.Message;
import com.basicemail.entity.MessageDto;

public class SendingMessageService {

	private ComposeMessageDao cmd;

	public SendingMessageService() {
		cmd = new ComposeMessageDao();
	}

	public String composeMessage(Message message, String recivers) {
		try {
			return cmd.composeMessage(message, recivers);
		} catch (SQLException e) {
			return null;
		}
	}

	public String replayMessage(Message message, String recivers, int threadid) {
		try {
			return cmd.replayMessage(message, recivers, threadid);
		} catch (SQLException e) {
			return null;
		}
	}

	public String forwardMessage(Message message, String recivers) {
		try {
			return cmd.composeMessage(message, recivers);
		} catch (SQLException e) {
			return null;
		}
	}

}
