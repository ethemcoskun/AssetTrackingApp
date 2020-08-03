package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.User;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserid(rs.getInt("userid"));
		user.setUsername(rs.getString("username"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setTitle(rs.getString("title"));
		user.setLastlogin(rs.getString("lastlogin"));
		user.setPassword(rs.getString("password"));
		user.setRoleid(rs.getInt("roleid"));
		return user;
	}

	
}
