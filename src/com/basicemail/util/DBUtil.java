package com.basicemail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	final static String DB_URL = "jdbc:mysql://localhost:3306/ia-basic-email?useUnicode=yes&characterEncoding=UTF-8";
	final static String DB_USER_NAME = "root";
	final static String DB_PASSWORD = "root";
	final static String DRIVER_NAME = "com.mysql.jdbc.Driver";

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
	}

	public static void closeConnection(Connection con) throws SQLException {

		if (con != null && !con.isClosed()) {
			con.close();
		}

	}

	public static void cleanUp(Connection con, PreparedStatement pre, ResultSet rs) throws SQLException {

		if (rs != null && !rs.isClosed()) {
			rs.close();
		}

		if ((pre != null && !pre.isClosed())) {
			pre.close();
		}

		closeConnection(con);
	}
}
