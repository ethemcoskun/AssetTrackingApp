package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Role;

public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Role role = new Role();
		
		role.setRoleid(rs.getInt("roleid"));
		role.setRolename(rs.getString("rolename"));
		
		return role;
	}

	
}
