package com.app.AssetTrackingApp.model;
 
 
public class Appright {
 
	private int rightid; 
	private String right_description;
	private String right_code;
	
	
	public Appright() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Appright(String right_description) {
		super(); 
		this.right_description = right_description;
	}

	public int getRightid() {
		return rightid;
	} 
	public void setRightid(int rightid) {
		this.rightid = rightid;
	} 
	public String getRight_description() {
		return right_description;
	}
	public void setRight_description(String right_description) {
		this.right_description = right_description;
	}

	public String getRight_code() {
		return right_code;
	}

	public void setRight_code(String right_code) {
		this.right_code = right_code;
	}
	
	
}
