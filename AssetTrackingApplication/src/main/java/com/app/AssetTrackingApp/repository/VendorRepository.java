package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
import com.app.AssetTrackingApp.mapper.VendorMapper; 
import com.app.AssetTrackingApp.model.Vendor;

@Repository
public class VendorRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Vendor> getAllVendors(){
		
		List<Vendor> vendorList = new ArrayList<Vendor>();
		List<Vendor> vendors = jdbctemplate.query("Select * from vendor;", new VendorMapper());
		vendorList.addAll(vendors);
		
		return vendorList;
				
	}
	
	public void addNewVendor(Vendor vendor) {
		String sql = "INSERT INTO vendor (vendorname, vendordescription, vendorwebsite, vendoraddress) VALUES ( ?, ?, ?, ?);";
		jdbctemplate.update(sql, vendor.getVendor_name(), vendor.getVendor_description(), vendor.getVendor_website(), vendor.getVendor_address());
	}

	public void updateVendor(Vendor vendor) {
		String sql = "UPDATE vendor SET vendorname = ?, vendordescription = ?, vendorwebsite = ?, vendoraddress = ? WHERE vendorid = ?;";
		jdbctemplate.update(sql, vendor.getVendor_name(), vendor.getVendor_description(), vendor.getVendor_website(), vendor.getVendor_address(), vendor.getVendor_id());
	}
	
	public void deleteVendor(int vendor_id) {
		String sql = "DELETE FROM vendor WHERE vendorid = ?;";
		jdbctemplate.update(sql, vendor_id);
	}
	
	public Vendor findVendorById(int vendor_id) { 
		String sql = "SELECT * FROM vendor WHERE vendorid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {vendor_id}, new VendorMapper());
	}
	
	public Vendor getVendorByVendorName(String vendorName) { 
		String sql = "SELECT * FROM vendor WHERE vendorname = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {vendorName}, new VendorMapper());
	}
	
}
