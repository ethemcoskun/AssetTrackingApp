package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.AssetTrackingApp.mapper.AssetMapper;
import com.app.AssetTrackingApp.mapper.SiteMapper;
import com.app.AssetTrackingApp.mapper.VendorMapper;
import com.app.AssetTrackingApp.model.Asset;
import com.app.AssetTrackingApp.model.Site;
import com.app.AssetTrackingApp.model.Vendor; 

@Repository
public class AssetRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Asset> getAssetsBySiteId(int locationSiteId){
		
		List<Asset> site_asset_list = new ArrayList<Asset>();
		String sql = "SELECT * FROM asset WHERE location_site_id = " + locationSiteId + ";";
		List<Asset> assets = jdbctemplate.query(sql, new AssetMapper());
		site_asset_list.addAll(assets);
		
		return site_asset_list;
	}
	
	public List<Asset> getAssetsByLocationId(int location_id){
		List<Asset> locationAssets = new ArrayList<Asset>();
		String sql = "SELECT * FROM asset WHERE location_id = " + location_id + ";";
		locationAssets.addAll(jdbctemplate.query(sql, new AssetMapper()));
		return locationAssets;		
	}
	
	public Asset findAssetById(long asset_id) {
		String sql = "SELECT * FROM asset WHERE assetid = ?;";
		return jdbctemplate.queryForObject(sql, new Object [] {asset_id}, new AssetMapper());
	}
	
	public Vendor getVendorByAssetId(long asset_id) {
		String sql = "SELECT v.* FROM vendor v inner JOIN asset_db.asset ON vendor_id = v.vendorid WHERE asset.assetid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {asset_id}, new VendorMapper());
	}
	
	public List<Asset> getAssetsByCategoryId(int category_id) {
		String sql = "SELECT * FROM asset WHERE category_id = ?;";
		return jdbctemplate.query(sql, new AssetMapper(), category_id);
	}
	
	public void addNewAsset(Asset asset) {
		String sql = "INSERT INTO asset (assetname, assetdescription, assettag, ponumber, quantity, purchasedate, purchasecost, salvagevalue, is_lost, is_broken, vendor_id, category_id, location_id, location_site_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		jdbctemplate.update(sql, asset.getAsset_name(), asset.getAsset_description(), asset.getAsset_tag(), asset.getPo_number(), asset.getQuantity(), asset.getPurchase_date(), asset.getPurchase_cost(), asset.getSalvage_value(), asset.isIs_lost(), asset.isIs_broken(), asset.getVendor_id(), asset.getCategory_id(), asset.getLocation_id(), asset.getLocation_site_id());
	}

	public void updateAsset(Asset asset) {
		String sql = "UPDATE asset SET assetname = ?, assetdescription = ?, assettag = ?, ponumber = ?, quantity = ?, purchasedate = ?, purchasecost = ?, salvagevalue = ?, is_lost = ?, is_broken = ?, vendor_id = ?, category_id = ?, location_id = ?, location_site_id = ? WHERE assetid = ?;";
		System.out.println("===========");
		System.out.println(asset.getVendor_id());
		System.out.println(asset.getCategory_id());
		System.out.println("loc" + asset.getLocation_id());
		System.out.println(asset.getLocation_site_id());
		System.out.println(asset.getAsset_id());
		
		jdbctemplate.update(sql, asset.getAsset_name(), asset.getAsset_description(), asset.getAsset_tag(), asset.getPo_number(), asset.getQuantity(), asset.getPurchase_date(), asset.getPurchase_cost(), asset.getSalvage_value(), asset.isIs_lost(), asset.isIs_broken(), asset.getVendor_id(), asset.getCategory_id(), asset.getLocation_id(), asset.getLocation_site_id(), asset.getAsset_id());
	}
	
	public void deleteAsset(long asset_id) {
		String sql = "DELETE FROM asset WHERE assetid = ?";
		jdbctemplate.update(sql, asset_id);
	}
	
	public Site getSiteByAssetId(long asset_id) { 
		String sql = "SELECT site.* FROM site inner join asset on site.siteid = asset.location_site_id WHERE asset.assetid = ?";
		 return jdbctemplate.queryForObject(sql, new Object[] {asset_id}, new SiteMapper());		
	}
	
}
