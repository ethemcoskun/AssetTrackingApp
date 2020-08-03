package com.app.AssetTrackingApp.model;

import javax.validation.constraints.NotEmpty;

public class Vendor {
	
	private int vendor_id;
	@NotEmpty(message = "Vendor name must not be empty.")
	private String vendor_name;
	private String vendor_description;
	private String vendor_website;
	private String vendor_address;
		
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendor(int vendor_id, String vendor_name, String vendor_description, String vendor_website,
			String vendor_address) {
		super();
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.vendor_description = vendor_description;
		this.vendor_website = vendor_website;
		this.vendor_address = vendor_address;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public String getVendor_description() {
		return vendor_description;
	}
	public void setVendor_description(String vendor_description) {
		this.vendor_description = vendor_description;
	}
	public String getVendor_website() {
		return vendor_website;
	}
	public void setVendor_website(String vendor_website) {
		this.vendor_website = vendor_website;
	}
	public String getVendor_address() {
		return vendor_address;
	}
	public void setVendor_address(String vendor_address) {
		this.vendor_address = vendor_address;
	}
	
	
	
	
}
