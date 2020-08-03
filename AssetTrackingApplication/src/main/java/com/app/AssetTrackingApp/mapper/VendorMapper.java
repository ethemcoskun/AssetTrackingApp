package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Vendor;

public class VendorMapper implements RowMapper<Vendor> {

	@Override
	public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Vendor vendor = new Vendor();
		
		vendor.setVendor_id(rs.getInt("vendorid"));
		vendor.setVendor_name(rs.getString("vendorname"));
		vendor.setVendor_description(rs.getString("vendordescription"));
		vendor.setVendor_website(rs.getString("vendorwebsite"));
		vendor.setVendor_address(rs.getString("vendoraddress"));
		
		return vendor;
	}
	
	
}
