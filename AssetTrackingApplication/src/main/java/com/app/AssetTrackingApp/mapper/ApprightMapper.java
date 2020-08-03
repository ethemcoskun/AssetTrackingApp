package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Appright;

public class ApprightMapper implements RowMapper<Appright>{

	@Override
	public Appright mapRow(ResultSet rs, int rowNum) throws SQLException {

		Appright right = new Appright();
		
		right.setRightid(rs.getInt("rightid"));
		right.setRight_description(rs.getString("right_description"));
		right.setRight_code(rs.getString("right_code"));
		
		return right;
	}
}
