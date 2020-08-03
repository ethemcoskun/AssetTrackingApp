package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.AssetTrackingApp.mapper.SiteMapper;
import com.app.AssetTrackingApp.model.Location;
import com.app.AssetTrackingApp.model.Site;

@Repository
public class SiteRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Autowired
	LocationRepository locationRepository;
	
	public List<Site> getAllSites() {
		
		List<Site> all_sites = new ArrayList<Site>();
		List<Site> sites = jdbctemplate.query("SELECT * FROM site;", new SiteMapper());
		all_sites.addAll(sites);
		return sites;
	}

	public List<Integer> getSiteIDs() {
		 
		String sql = "SELECT siteid FROM site;";
		List<Integer> sitesIDs = jdbctemplate.queryForList(sql, Integer.class);
		return sitesIDs;
	}
	
	public void addSite(Site site) {
		String sql = "INSERT INTO site (sitename, siteaddress, sitephone, sitenotes) VALUES ( ?, ?, ?, ?);";
		jdbctemplate.update(sql, site.getSite_name(), site.getSite_address(), site.getSite_phone(), site.getSite_notes());
		
		Location location = new Location();
		location.setLocation_name("Temp Room");
		location.setLocation_description("Temporary room for new assets");
		int siteId = getSiteBySiteName(site.getSite_name()).getSite_id();
		location.setLocation_siteId(siteId);
		locationRepository.addLocation(location);
			
	}
	
	public void deleteSite(int site_id) {
		locationRepository.deleteAllLocationsInSite(site_id);
		String sql = "DELETE FROM site WHERE siteid = ?;";
		jdbctemplate.update(sql, site_id);
	}
	
	public Site findBySiteId(int site_id) {
		String sql = "SELECT * FROM site WHERE siteid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {site_id}, new SiteMapper());
	}
	
	public Site getSiteBySiteName(String siteName) {
		String sql = "SELECT * FROM site WHERE sitename = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {siteName}, new SiteMapper());
	}
}
