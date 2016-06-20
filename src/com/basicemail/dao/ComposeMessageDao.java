package com.basicemail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.basicemail.entity.Message;
import com.basicemail.entity.MessageDto;
import com.basicemail.entity.User;
import com.basicemail.util.DBUtil;
import com.mysql.jdbc.Statement;

public class ComposeMessageDao {

	private Message insertMessage(Message message) throws SQLException {
		String composeSql = "INSERT INTO message (sender_id,subject,body) VALUES (?,?,?);";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(composeSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, message.getSenderId());
			ps.setString(2, message.getSubject());
			ps.setString(3, message.getBody());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					message.setId((int) generatedKeys.getLong(1));
				} else {
					isSuccess = false;
				}
			}
		} 
		catch(Exception d){d.printStackTrace();}
		finally {
			ps.close();
			con.close();
			if (isSuccess)
				return message;
			else
				return null;
		}
	}

	private boolean updateThreadMessageId(int messageId, int threadMessageId) throws SQLException {
		String insetThreadMessageIdSql = "UPDATE message SET thread_msg_id = ? WHERE id = ?;";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(insetThreadMessageIdSql);
			ps.setInt(1, threadMessageId);
			ps.setInt(2, messageId);
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}
		} finally {
			ps.close();
			con.close();
			return isSuccess;
		}
	}

	private boolean deleteMessage(int messageId) throws SQLException {
		String insetThreadMessageIdSql = "DELETE FROM message WHERE id = ?;";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(insetThreadMessageIdSql);
			ps.setInt(1, messageId);

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}
		} finally {
			ps.close();
			con.close();
			return isSuccess;
		}
	}

	private String[] getEmailList(String recivers) {
		// ArrayList<String> list = new ArrayList<>();
		// String[] temp = recivers.split(",");
		// return list;
		return recivers.split(",");
	}

	public User getSingleUserByEmail(String email) throws SQLException {
		String selectSQL = "SELECT * from user WHERE email = ? ;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		boolean isSuccess = false;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				int id = rs.getInt("id");
				user = new User(id, firstname, lastname, email);
				isSuccess = true;
			}
		} finally {
			rs.close();
			ps.close();
			con.close();
			if (isSuccess)
				return user;
			else
				return null;
		}
	}

	private List<User> getUsersByEmail(String[] emailList) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		for (String string : emailList) {
			list.add(getSingleUserByEmail(string));
		}
		return list;
	}

	private boolean insertIntoReciver(int messageId, int reciverId, int threadID) throws SQLException {
		String composeSql = "INSERT INTO recipient_message (thread_msg_id,msg_id,reciver_id) VALUES (?,?,?);";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(composeSql);
			ps.setInt(1, threadID);
			ps.setInt(2, messageId);
			ps.setInt(3, reciverId);

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}

		} finally {
			ps.close();
			con.close();
			return isSuccess;
		}
	}

	private boolean removeFromReciver(int messageId, int reciverId, int threadID) throws SQLException {
		String composeSql = "DELETE FROM recipient_message WHERE thread_msg_id= ? and msg_id= ? and reciver_id = ?;";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(composeSql);
			ps.setInt(1, threadID);
			ps.setInt(2, messageId);
			ps.setInt(3, reciverId);
			ps.executeUpdate();
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}

		} finally {
			ps.close();
			con.close();
			return isSuccess;
		}
	}

	private boolean insertIntoOperation(int userid, int threadID) throws SQLException {
		String composeSql = "INSERT INTO opertaions (userID,thredID) VALUES (?,?);";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(composeSql);
			ps.setInt(1, userid);
			ps.setInt(2, threadID);

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}

		} finally {
			ps.close();
			con.close();
			return isSuccess;
		}
	}

	private boolean deleteFromOperation(int userid, int threadID) throws SQLException {
		String composeSql = "DELETE FROM opertaions WHERE userID=? and thredID=? ;";
		boolean isSuccess = true;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(composeSql);
			ps.setInt(1, userid);
			ps.setInt(2, threadID);

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				isSuccess = false;
			}

		} finally {
			ps.close();
			con.close();
			return isSuccess;
		}
	}

	public String composeMessage(Message message, String recivers) throws SQLException {

		String status = "";

		message = insertMessage(message);

		if (message == null) {
			return "Message is deleted";
		}

		message.setThreadMsgId(message.getId());

		if (!updateThreadMessageId(message.getId(), message.getThreadMsgId())) {
			deleteMessage(message.getId());
			return "Message is deleted";
		}

		int numOfReciver = 0, i = 0;
		String[] emailList = getEmailList(recivers);
		List<User> UsersList = getUsersByEmail(emailList);
		for (User user : UsersList) {
			if (user == null) {
				status += "Cann't find user by email " + emailList[i] + ",";
			} else {
				boolean flag1 = insertIntoReciver(message.getId(), user.getId(), message.getThreadMsgId());
				boolean flag2 = insertIntoOperation(user.getId(), message.getThreadMsgId());
				if (flag1 && flag2)
					numOfReciver++;
				else {
					status += "Something went wrong when trying to send to " + emailList[i] + ",";
					if (flag1) {
						removeFromReciver(message.getId(), user.getId(), message.getThreadMsgId());
					} else if (flag2) {
						deleteFromOperation(user.getId(), message.getThreadMsgId());
					}
				}
			}
			i++;
		}

		if (numOfReciver == 0 || !insertIntoOperation(message.getSenderId(), message.getThreadMsgId())) {
			status += "Message is deleted";
			deleteMessage(message.getId());
			return status;
		}

		return status + "Message has been sent";
	}

	public String replayMessage(Message message, String recivers, int threadid) throws SQLException {

		String status = "";

		message = insertMessage(message);

		if (message == null) {
			return "Message is deleted";
		}

		message.setThreadMsgId(threadid);

		if (!updateThreadMessageId(message.getId(), message.getThreadMsgId())) {
			deleteMessage(message.getId());
			return "Message is deleted";
		}

		int numOfReciver = 0, i = 0;
		String[] emailList = getEmailList(recivers);
		List<User> UsersList = getUsersByEmail(emailList);
		for (User user : UsersList) {
			if (user == null) {
				status += "Cann't find user by email " + emailList[i] + ",";
			} else {
				boolean flag1 = insertIntoReciver(message.getId(), user.getId(), message.getThreadMsgId());
				if (flag1)
					numOfReciver++;
				else if (flag1) {
					status += "Something went wrong when trying to send to " + emailList[i] + ",";
					removeFromReciver(message.getId(), user.getId(), message.getThreadMsgId());
				}
			}
			i++;
		}

		if (numOfReciver == 0 || !insertIntoOperation(message.getSenderId(), message.getThreadMsgId())) {
			status += "Message is deleted";
			deleteMessage(message.getId());
			return status;
		}

		return status + "Message has been sent";
	}
}
