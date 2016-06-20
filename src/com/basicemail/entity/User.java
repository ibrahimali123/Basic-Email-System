/**
 * Create On: Dec 20, 2015 8:48:17 PM
 * @author mohamed265
 */
package com.basicemail.entity;

/**
 * @author mohamed265
 */
public class User {

	private int id;

	private String firstname;

	private String lastname;

	private String password;

	private String photourl;

	private String phone;

	private String email;

	public User() {
	}

	public User(int id, String firstname, String lastname, String password, String photourl, String phone,
			String email) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.photourl = photourl;
		this.phone = phone;
		this.email = email;
	}

	public User(int id, String firstname, String lastname, String email) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
				+ ", photourl=" + photourl + ", phone=" + phone + ", email=" + email + "]";
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getPhotourl() {
		return photourl;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
