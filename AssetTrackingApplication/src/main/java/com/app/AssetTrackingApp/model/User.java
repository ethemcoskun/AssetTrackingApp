package com.app.AssetTrackingApp.model;
 
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
 
	private int userid;
	@NotEmpty(message = "Username must not be empty")
	@Size(min = 2, max = 10, message = "Username must be between 2 - 10 characters")
	private String username;
	@NotEmpty(message = "Firstname must not be empty")
	private String firstname;
	@NotEmpty(message = "Lastname must not be empty")
	private String lastname;
	@NotEmpty(message = "User email must not be empty")
	private String email; 
	@NotEmpty(message = "User password must not be empty")
	@Size(min = 6, max = 20, message = "Password must be between 6 - 20 characters")
	private String password; 
	private String title;
	private String lastlogin;
	private int roleid;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int userid, String username, String firstname, String lastname, String email, String password,
			String title, String lastlogin, int roleid) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.title = title;
		this.lastlogin = lastlogin;
		this.roleid = roleid;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLastlogin() {
		return lastlogin;
	}


	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}


	public int getRoleid() {
		return roleid;
	}


	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	 
 
	
	
}
