package com.app.AssetTrackingApp.repository;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
import com.app.AssetTrackingApp.mapper.UserMapper; 
import com.app.AssetTrackingApp.model.User;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<User> getAllUsers(){
		List<User> all_users = new ArrayList<User>();
		 all_users.addAll(jdbctemplate.query("SELECT * FROM user;", new UserMapper()));
	 
		return all_users;
	} 
	
	public void addUser(User user) {
		String sql = "INSERT INTO user (username, firstname, lastname, email, password, title, roleid) VALUES (?, ?, ?, ?, ?, ?, ?);";
		jdbctemplate.update(sql, user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getTitle(), user.getRoleid());
	}
	
	public void deleteUser(int userid) {
		String sql = "DELETE FROM user WHERE userid = ?;";
		jdbctemplate.update(sql, userid);
	}

	public User findUserById(int userid) {
		String sql = "SELECT * FROM user WHERE userid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {userid}, new UserMapper());
	}

	public void updateUser(User user) {
		String sql = "UPDATE user SET username = ?, firstname = ?, lastname = ?, email = ?, password = ?, title = ?, roleid = ? WHERE userid = ?;";
		String pass = ""; 
		if(user.getPassword() == null || user.getPassword() == "") {
			User userPre = findUserById(user.getUserid());
			pass = userPre.getPassword();
		}
		else{
			pass = user.getPassword();
		}
		jdbctemplate.update(sql, user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), pass, user.getTitle(), user.getRoleid(), user.getUserid());		
	}
}
