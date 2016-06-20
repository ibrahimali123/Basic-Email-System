package com.basicemail.dao;

import com.basicemail.entity.MessageDto;
import com.basicemail.entity.User;
import com.basicemail.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	public UserDao() {
	}

	public void addUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `ia-basic-email`.`user` (`firstname`, `lastname`, `password`, `email`) VALUES (?, ?, ?, ?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getEmail());
			System.out.println(ps);
			int x = ps.executeUpdate();
			System.out.println(x);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e2) {
			}

		}

	}

	public boolean emailIsAlreadyExist(String email) throws SQLException {
		String selectSQL = "SELECT * FROM `ia-basic-email`.`user` WHERE email = ? ";
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setString(1, email);
			System.out.println(ps);

			rs = ps.executeQuery();
			while (rs.next()) {
				String retEmail = rs.getString("email");
				list.add(retEmail);
			}

			if (list.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
		} finally {
			rs.close();
			ps.close();
			con.close();
		}

		return false;
	}

	public User getUser(String email, String password) {
		String selectSQL = "SELECT * FROM `ia-basic-email`.`user` WHERE email = ? AND password = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setString(1, email);
			ps.setString(2, password);
			System.out.println(ps);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String pass = rs.getString("password");
				String photo = rs.getString("photourl");
				String phone = rs.getString("phone");
				String emai = rs.getString("email");
				user = new User(id, firstName, lastName, pass, photo, phone, emai);

			}
		} catch (Exception e) {
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e2) {
			}
		}

		return user;

	}

	public User getUser(String id) {

		String selectSQL = "SELECT * FROM `ia-basic-email`.`user` WHERE id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, Integer.parseInt(id));
			System.out.println(ps);

			rs = ps.executeQuery();
			while (rs.next()) {

				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String pass = rs.getString("password");
				String photo = rs.getString("photourl");
				String phone = rs.getString("phone");
				String emai = rs.getString("email");
				user = new User(Integer.parseInt(id), firstName, lastName, pass, photo, phone, emai);
			}

		} catch (Exception e) {
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e2) {
			}
		}
		return user;
	}

	public void updateProfile(int id, String firstName, String lastname, String phone) {
		System.out.println("daoooooo");
		String updateSQL = "UPDATE `ia-basic-email`.`user` SET `firstname` = ? , `lastname` = ? , `phone` = ? WHERE id = ?";
		System.out.println(updateSQL);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateSQL);
			ps.setString(1, firstName);
			ps.setString(2, lastname);
			ps.setString(3, phone);
			ps.setInt(4, id);
			ps.executeUpdate();

			System.out.println(ps);
		} catch (Exception e) {
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e2) {
			}
		}
	}
}
