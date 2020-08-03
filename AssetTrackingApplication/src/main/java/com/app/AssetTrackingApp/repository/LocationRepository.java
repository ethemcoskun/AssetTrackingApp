package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
import com.app.AssetTrackingApp.mapper.LocationMapper;
import com.app.AssetTrackingApp.mapper.SiteMapper;
import com.app.AssetTrackingApp.model.Location;
import com.app.AssetTrackingApp.model.Site;

@Repository
public class LocationRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Location> getLocationsBySiteId(int site_id){ 
		String sql = "SELECT * FROM location WHERE siteid = " + site_id + ";";
		List<Location> locationsBySite = jdbctemplate.query(sql, new LocationMapper());
		 
		return locationsBySite;
	}
	
	public List<Location> getAllLocations() {
		String sql = "SELECT * FROM location;";
		return jdbctemplate.query(sql, new LocationMapper());
	}
	
	public ArrayList<String> getLocationNameBySiteId(int site_id){
		String sql = "SELECT locationname FROM location WHERE siteid = " + site_id + ";";
		ArrayList<String> locationsBySite = (ArrayList<String>) jdbctemplate.queryForList(sql, String.class);
		return locationsBySite;
	}
	
	public void addLocation(Location location) {
		String sql = "INSERT INTO location ( locationname, locationdescription, siteid) VALUES ( ?, ?, ?);";
		jdbctemplate.update(sql, location.getLocation_name(), location.getLocation_description(), location.getLocation_siteId());
	}
	
	public Location findLocationById(int location_id) {
		String sql = "SELECT * FROM location WHERE locationid = ?;";
		return jdbctemplate.queryForObject(sql, new Object [] {location_id}, new LocationMapper());
	}
	
	public void deleteLocation(int location_id, int site_id) {
		String sql = "DELETE FROM location WHERE locationid = ? and siteid = ?";
						
		jdbctemplate.update(sql, location_id, site_id);
	}
	
	public void deleteAllLocationsInSite(int siteid) {
		String sql = "DELETE FROM location WHERE siteid = ?";

		jdbctemplate.update(sql, siteid);
	}
	
	public Site getSiteByLocationId(int location_id) {
		String sql = "SELECT site.* FROM site inner JOIN location ON site.siteid = location.siteid WHERE location.locationid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {location_id}, new SiteMapper());
	}

	public Location getTempLocationBySiteId(int siteid) {
		String locationName = "Temp Room";
		String sql = "SELECT * FROM location WHERE locationname = ? and siteid = ?;";
		return jdbctemplate.queryForObject(sql, new Object [] {locationName, siteid}, new LocationMapper());
	}
}
