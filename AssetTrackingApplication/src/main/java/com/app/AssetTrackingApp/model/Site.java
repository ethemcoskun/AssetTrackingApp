package com.app.AssetTrackingApp.model;

public class Site {
	
	private int site_id;
	private String site_name;
	private String site_address;
	private String site_phone;
	private String site_notes;
	
	
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Site(int site_id, String site_name, String site_address, String site_phone, String site_notes) {
		super();
		this.site_id = site_id;
		this.site_name = site_name;
		this.site_address = site_address;
		this.site_phone = site_phone;
		this.site_notes = site_notes;
	}
	public int getSite_id() {
		return site_id;
	}
	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getSite_address() {
		return site_address;
	}
	public void setSite_address(String site_address) {
		this.site_address = site_address;
	}
	public String getSite_phone() {
		return site_phone;
	}
	public void setSite_phone(String site_phone) {
		this.site_phone = site_phone;
	}
	public String getSite_notes() {
		return site_notes;
	}
	public void setSite_notes(String site_notes) {
		this.site_notes = site_notes;
	}
	
	
}
