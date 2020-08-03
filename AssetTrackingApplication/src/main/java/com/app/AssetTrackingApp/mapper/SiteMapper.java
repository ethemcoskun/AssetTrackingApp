package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Site;

public class SiteMapper implements RowMapper<Site> {

	@Override
	public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Site site = new Site();
		
		site.setSite_id(rs.getInt("siteid"));
		site.setSite_name(rs.getString("sitename"));
		site.setSite_address(rs.getString("siteaddress"));
		site.setSite_notes(rs.getString("sitenotes"));
		site.setSite_phone(rs.getString("sitephone"));
		
		return site;
	}

}
