package com.app.AssetTrackingApp.model;

import javax.validation.constraints.Min; 
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Asset {
	
	private long asset_id;
	@NotEmpty(message = "Asset name must not be empty.")
	@Size(min = 2, message = "Asset name has to be at least 2 letters long.")
	private String asset_name;
	private String asset_description;
	@NotEmpty(message = "Asset tag must not be empty.")
	private String asset_tag;
	private String po_number;
	@Min(value = 1, message = "Quantity must be at least 1")
	private int quantity;
	@NotNull(message = "Please select a date")
	private String purchase_date;
	private double purchase_cost;
	private String record_date;
	private double salvage_value;
	private boolean is_lost;
	private boolean is_broken;
	
	private int vendor_id;
	private int category_id;
	private int location_id;
	private int location_site_id;

 
	public Asset() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Asset(long asset_id, String asset_name, String asset_description, String asset_tag, String po_number, int quantity,
			String purchase_date, double purchase_cost, String record_date, double salvage_value, boolean is_lost,
			boolean is_broken, int vendor_id, int category_id, int location_id, int location_site_id) {
		super();
		this.asset_id = asset_id;
		this.asset_name = asset_name;
		this.asset_description = asset_description;
		this.asset_tag = asset_tag;
		this.po_number = po_number;
		this.quantity = quantity;
		this.purchase_date = purchase_date;
		this.purchase_cost = purchase_cost;
		this.record_date = record_date;
		this.salvage_value = salvage_value;
		this.is_lost = is_lost;
		this.is_broken = is_broken;
		this.vendor_id = vendor_id;
		this.category_id = category_id;
		this.location_id = location_id;
		this.location_site_id = location_site_id;
	}
	
	
	
	public long getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(long asset_id) {
		this.asset_id = asset_id;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getAsset_description() {
		return asset_description;
	}
	public void setAsset_description(String asset_description) {
		this.asset_description = asset_description;
	}
	public String getAsset_tag() {
		return asset_tag;
	}
	public void setAsset_tag(String asset_tag) {
		this.asset_tag = asset_tag;
	}
	public String getPo_number() {
		return po_number;
	}
	public void setPo_number(String po_number) {
		this.po_number = po_number;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPurchase_date() {
//		if(purchase_date.isEmpty())
//			return "2020-01-01";
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		if(purchase_date.isEmpty()) {
			this.purchase_date = "2020-01-01";
		}
		else {
		this.purchase_date = purchase_date;
		}
	}
	public double getPurchase_cost() {
		return purchase_cost;
	}
	public void setPurchase_cost(double purchase_cost) {
		this.purchase_cost = purchase_cost;
	}
	public String getRecord_date() {
		return record_date;
	}
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
	public double getSalvage_value() {
		return salvage_value;
	}
	public void setSalvage_value(double salvage_value) {
		this.salvage_value = salvage_value;
	}
	public boolean isIs_lost() {
		return is_lost;
	}
	public void setIs_lost(boolean is_lost) {
		this.is_lost = is_lost;
	}
	public boolean isIs_broken() {
		return is_broken;
	}
	public void setIs_broken(boolean is_broken) {
		this.is_broken = is_broken;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getLocation_site_id() {
		return location_site_id;
	}
	public void setLocation_site_id(int location_site_id) {
		this.location_site_id = location_site_id;
	}
	@Override
	public String toString() {
		return " |Asset Name: " + asset_name
				+ "|\n |Description:" + asset_description
				+ "|\n |Asset Tag:" + asset_tag
				+ "|\n |PO Number:" + po_number
				+ "|\n |Quantity:" + quantity
				+ "|\n |Purchase Date:" + purchase_date
				+ "|\n |Purchase Cost:" + purchase_cost
				+ "|\n |Record Date:" + record_date
				+ "|\n |Salvage Value:" + salvage_value
				+ "|\n |Lost:" + is_lost
				+ "|\n |Broken:" + is_broken + "|";
	}
	
	
	
}
