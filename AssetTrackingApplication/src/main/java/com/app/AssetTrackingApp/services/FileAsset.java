package com.app.AssetTrackingApp.services;

 
public class FileAsset {
	
	private String asset_name;
	private String asset_description;
	private String asset_tag;
	private String po_number;
	private int quantity;
	private String purchase_date;
	private double purchase_cost; 
	private double salvage_value;
	private boolean is_lost;
	private boolean is_broken;	
	private String vendorName;
	
	private int siteid;
	private int categoryid;
	private int locationid;
	private int vendorid;
	
	public FileAsset() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileAsset(String asset_name, String asset_description, String asset_tag, String po_number, int quantity,
			String purchase_date, double purchase_cost, double salvage_value, boolean is_lost, boolean is_broken,
			String vendorName) {
		super();
		this.asset_name = asset_name;
		this.asset_description = asset_description;
		this.asset_tag = asset_tag;
		this.po_number = po_number;
		this.quantity = quantity;
		this.purchase_date = purchase_date;
		this.purchase_cost = purchase_cost;
		this.salvage_value = salvage_value;
		this.is_lost = is_lost;
		this.is_broken = is_broken;
		this.vendorName = vendorName;
		
	}

	public int getSiteid() {
		return siteid;
	}

	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
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
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public double getPurchase_cost() {
		return purchase_cost;
	}

	public void setPurchase_cost(double purchase_cost) {
		this.purchase_cost = purchase_cost;
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	

	@Override
	public String toString() {
		return "FileAsset [asset_name=" + asset_name + ", asset_description=" + asset_description + ", asset_tag="
				+ asset_tag + ", po_number=" + po_number + ", quantity=" + quantity + ", purchase_date=" + purchase_date
				+ ", purchase_cost=" + purchase_cost + ", salvage_value=" + salvage_value + ", is_lost=" + is_lost
				+ ", is_broken=" + is_broken + ", vendorName=" + vendorName + "]";
	}
	
	

}
