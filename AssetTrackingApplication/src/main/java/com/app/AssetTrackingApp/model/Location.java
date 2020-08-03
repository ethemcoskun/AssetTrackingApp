package com.app.AssetTrackingApp.model;

import javax.validation.constraints.NotEmpty;

public class Location {
	
	private int location_id;
	@NotEmpty(message = "Location name must not be empty.")
	private String location_name;
	private String location_description;
	private int location_siteId;
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(int location_id, String location_name, String location_description, int location_siteId) {
		super();
		this.location_id = location_id;
		this.location_name = location_name;
		this.location_description = location_description;
		this.location_siteId = location_siteId;
	}

	
	public int getLocation_siteId() {
		return location_siteId;
	}
	public void setLocation_siteId(int location_siteId) {
		this.location_siteId = location_siteId;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLocation_description() {
		return location_description;
	}
	public void setLocation_description(String location_description) {
		this.location_description = location_description;
	}
	
	
}
