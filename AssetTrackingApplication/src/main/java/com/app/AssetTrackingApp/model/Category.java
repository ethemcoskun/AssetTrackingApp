package com.app.AssetTrackingApp.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Category {
	
	private int category_id;
	@NotNull
	@Size(min = 3, message = "Category name must not be blank!")
	private String category_name;
	@Range(min = 1, message = "Depreciation year must be at least 1")
	private int depreciation_years;
	
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int category_id, String category_name, int depreciation_years) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.depreciation_years = depreciation_years;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getDepreciation_years() {
		return depreciation_years;
	}
	public void setDepreciation_years(int depreciation_years) {
		this.depreciation_years = depreciation_years;
	}
	
	
}
