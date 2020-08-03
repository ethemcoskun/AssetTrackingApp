package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Location;

public class LocationMapper implements RowMapper<Location>{

	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Location location = new Location();
		location.setLocation_id(rs.getInt("locationid"));
		location.setLocation_name(rs.getString("locationname"));
		location.setLocation_description(rs.getString("locationdescription"));
		location.setLocation_siteId(rs.getInt("siteid"));
		
		return location;
	}

}
