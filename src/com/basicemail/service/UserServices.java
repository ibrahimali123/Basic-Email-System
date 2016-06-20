package com.basicemail.service;

import java.sql.SQLException;

import com.basicemail.dao.UserDao;
import com.basicemail.entity.User;

public class UserServices {
	private static UserDao userDao;

	public UserServices() {
		userDao = new UserDao();
	}

	public void addUser(User user) {
		try {
			userDao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean emailAlreadyExist(String email) {
		try {
			return userDao.emailIsAlreadyExist(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User getUser(String email, String pass) {
		return userDao.getUser(email, pass);
	}

	public User getUserByID(String id) {
		return userDao.getUser(id);
	}

	public void updateProfile(int id, String firstName, String lastName, String phone) {
		System.out.println("serviceee");
		userDao.updateProfile(id, firstName, lastName, phone);
	}

}
