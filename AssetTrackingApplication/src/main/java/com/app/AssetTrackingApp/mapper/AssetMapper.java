package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Asset; 

public class AssetMapper implements RowMapper<Asset>{
		
	
	
	@Override
	public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Asset asset = new Asset(); 
		
		asset.setAsset_id(rs.getLong("assetid"));
		asset.setAsset_name(rs.getString("assetname"));
		asset.setAsset_description(rs.getString("assetdescription"));
		asset.setAsset_tag(rs.getString("assettag"));
		asset.setPo_number(rs.getString("ponumber"));
		asset.setQuantity(rs.getInt("quantity"));
		asset.setPurchase_date(rs.getString("purchasedate"));
		asset.setPurchase_cost(rs.getDouble("purchasecost"));
		asset.setRecord_date(rs.getString("recorddate"));
		asset.setSalvage_value(rs.getDouble("salvagevalue"));
		asset.setIs_lost(rs.getBoolean("is_lost"));
		asset.setIs_broken(rs.getBoolean("is_broken"));
		asset.setVendor_id(rs.getInt("vendor_id"));
		asset.setCategory_id(rs.getInt("category_id"));
		asset.setLocation_id(rs.getInt("location_id"));
		asset.setLocation_site_id(rs.getInt("location_site_id"));   
		return asset;
	}
	
}
