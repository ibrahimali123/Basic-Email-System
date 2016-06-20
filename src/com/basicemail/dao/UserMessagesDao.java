package com.basicemail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.basicemail.entity.Message;
import com.basicemail.entity.MessageDto;
import com.basicemail.entity.ThreadMessageDto;
import com.basicemail.entity.User;
import com.basicemail.util.DBUtil;

public class UserMessagesDao {

	public User getUserByID(int userID) throws SQLException {
		String selectSQL = "SELECT * from user WHERE id = ? ;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");

				user = new User(userID, firstname, lastname, email);
			}
			return user;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public List<MessageDto> getUserInbox(int userID) throws SQLException {

		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? or message.sender_id = ?) AND recipient_message.thread_msg_id IN ( "
				+ "SELECT recipient_message.thread_msg_id FROM recipient_message WHERE recipient_message.reciver_id = ? ) "
				+ "AND opertaions.is_archived = 0 AND opertaions.is_trashed = 0 AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			ps.setInt(2, userID);
			ps.setInt(3, userID);
			ps.setInt(4, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public List<MessageDto> getUserSent(int userID) throws SQLException {

		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? or message.sender_id = ?) AND recipient_message.thread_msg_id IN ( "
				+ "SELECT message.thread_msg_id FROM message WHERE message.sender_id = ? ) "
				+ "AND opertaions.is_archived = 0 AND opertaions.is_trashed = 0 AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			ps.setInt(2, userID);
			ps.setInt(3, userID);
			ps.setInt(4, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public static Message getMessageBYID(int messageID) throws SQLException {

		String selectSQL = "SELECT * from message WHERE id = ? ;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Message message = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, messageID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int sender_id = rs.getInt("sender_id");
				int thread_msg_id = rs.getInt("thread_msg_id");

				String subject = rs.getString("subject");
				String body = rs.getString("body");
				String attachment = rs.getString("attachment");
				Timestamp timestamp = rs.getTimestamp("timestap");

				message = new Message(messageID, sender_id, thread_msg_id, subject, body, attachment, timestamp);

			}
			return message;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public List<MessageDto> getUserArchived(int userID) throws SQLException {

		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? or message.sender_id = ?) "
				+ "AND opertaions.is_archived = 1 AND opertaions.is_trashed = 0 AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			ps.setInt(2, userID);
			ps.setInt(3, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

	}

	public List<MessageDto> getUserTrashed(int userID) throws SQLException {

		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? or message.sender_id = ?) "
				+ "AND opertaions.is_archived = 0 AND opertaions.is_trashed = 1 AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			ps.setInt(2, userID);
			ps.setInt(3, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

	}

	public void ArchiveThreadMessages(int userID, int threadID) throws SQLException {
		String recipient_message = "UPDATE opertaions SET opertaions.is_archived = 1 WHERE "
				+ "opertaions.thredID = ? AND opertaions.userID = ?;";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(recipient_message);
			ps.setInt(1, threadID);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} finally {
			ps.close();
			con.close();
		}
	}

	public void DeleteThreadMessages(int userID, int threadID) throws SQLException {
		String recipient_message = "UPDATE opertaions SET opertaions.is_deleted = 1 WHERE "
				+ "opertaions.thredID = ? AND opertaions.userID = ?;";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(recipient_message);
			ps.setInt(1, threadID);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} finally {
			ps.close();
			con.close();
		}
	}
	public void trashThreadMessages(int userID, int threadID) throws SQLException {
		String recipient_message = "UPDATE opertaions SET opertaions.is_trashed = 1 , opertaions.is_archived = 0 WHERE "
				+ "opertaions.thredID = ? AND opertaions.userID = ?;";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(recipient_message);
			ps.setInt(1, threadID);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} finally {
			ps.close();
			con.close();
		}
	}
	

	public void MarkThreadASReaded(int userID, int threadID) throws SQLException {
		String recipient_message = "UPDATE opertaions SET opertaions.is_readed = 1 WHERE "
				+ "opertaions.thredID = ? AND opertaions.userID = ?;";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(recipient_message);
			ps.setInt(1, threadID);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} finally {
			ps.close();
			con.close();
		}
	}

	public static String getAllMessageReceivers(int msgID, int threadID) throws SQLException {
		String selectSQL = "SELECT DISTINCT(user.email) FROM recipient_message "
				+ "INNER JOIN user ON user.id = recipient_message.reciver_id "
				+ "WHERE recipient_message.thread_msg_id = ? AND recipient_message.msg_id = ?;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, threadID);
			ps.setInt(2, msgID);

			rs = ps.executeQuery();

			String receiver = "";

			while (rs.next())
				if (rs.isLast())
					receiver += rs.getString("email");
				else
					receiver += rs.getString("email") + " , ";

			return receiver;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public List<ThreadMessageDto> getAllMessagesOfThreadByThreadID(int userID, int threadID) throws SQLException {
		String selectSQL = "SELECT DISTINCT message.id FROM message "
				+ "INNER JOIN recipient_message ON recipient_message.msg_id = message.id "
				+ "WHERE message.thread_msg_id = ? AND (recipient_message.reciver_id = ? OR message.sender_id = ?) "
				+ "ORDER BY message.timestap DESC;";

		List<ThreadMessageDto> list = new ArrayList<ThreadMessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, threadID);
			ps.setInt(2, userID);
			ps.setInt(3, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("id");

				String receiver = getAllMessageReceivers(msgID, threadID);
				Message message = getMessageBYID(msgID);
				String sender = getUserByID(message.getSenderId()).getEmail();

				ThreadMessageDto messageDto = new ThreadMessageDto(threadID, receiver, sender, message);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}
	public List<MessageDto> searchAboutThreadsByFrom(int userID, int wantedUserID) throws SQLException {
		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? AND message.sender_id = ?) "
				+ "AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			ps.setInt(2, wantedUserID);
			ps.setInt(3, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public List<MessageDto> searchAboutThreadsByTo(int userID, int wantedUserID) throws SQLException {
		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? AND message.sender_id = ? ) "
				+ "AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, wantedUserID);
			ps.setInt(2, userID);
			ps.setInt(3, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

	public List<MessageDto> searchAboutThreadsBySpecificRange(int userID, String from, String to) throws SQLException {
		String selectSQL = "SELECT MAX(message.id) as 'msgID' , recipient_message.thread_msg_id , opertaions.is_readed "
				+ "FROM recipient_message INNER JOIN message ON recipient_message.msg_id = message.id "
				+ "INNER JOIN opertaions ON recipient_message.thread_msg_id = opertaions.thredID "
				+ "WHERE (recipient_message.reciver_id = ? or message.sender_id = ?) AND (message.timestap BETWEEN ? AND ?)"
				+ "AND opertaions.is_deleted = 0 AND opertaions.userID = ? "
				+ "GROUP BY recipient_message.thread_msg_id DESC;";

		List<MessageDto> list = new ArrayList<MessageDto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, userID);
			ps.setInt(2, userID);
			ps.setString(3, from);
			ps.setString(4, to);
			ps.setInt(5, userID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int msgID = rs.getInt("msgID");
				int thread_msg_id = rs.getInt("thread_msg_id");
				boolean isReaded = rs.getBoolean("is_readed");

				Message message = getMessageBYID(msgID);
				User sender = getUserByID(message.getSenderId());

				int threadMessagesNumber = getAllMessagesOfThreadByThreadID(userID, thread_msg_id).size();
				MessageDto messageDto = new MessageDto(thread_msg_id, sender, message, threadMessagesNumber, isReaded);
				list.add(messageDto);
			}
			return list;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}
	public String getAllThreadUsersEmails(int userID, int threadID) throws SQLException {
		String selectSQL = "SELECT DISTINCT(user.email) FROM recipient_message "
				+ "INNER JOIN user ON user.id = recipient_message.reciver_id "
				+ "INNER JOIN message ON message.id = recipient_message.msg_id "
				+ "WHERE recipient_message.thread_msg_id = ? AND recipient_message.reciver_id <> ? "
				+ "UNION SELECT DISTINCT(user.email) as 'ss' FROM message "
				+ "INNER JOIN user ON user.id = message.sender_id "
				+ "WHERE message.thread_msg_id = ? AND message.sender_id <> ?;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, threadID);
			ps.setInt(2, userID);
			ps.setInt(3, threadID);
			ps.setInt(4, userID);
			
			rs = ps.executeQuery();

			String receiver = "";

			while (rs.next())
				if (rs.isLast())
					receiver += rs.getString("email");
				else
					receiver += rs.getString("email") + ",";

			return receiver;
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}

}
