package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.AssetTrackingApp.mapper.CategoryMapper;
import com.app.AssetTrackingApp.model.Category;

@Repository
public class CategoryRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Category> getAllCategories(){
		
		List<Category> categoryList = new ArrayList<Category>();
		List<Category> categories = jdbctemplate.query("Select * from category;", new CategoryMapper());
		categoryList.addAll(categories);
		
		return categoryList;
	}
	
	public void addNewCategory(Category category) {
		String sql = "INSERT INTO category (categoryname, depreciationyears) VALUES ( ?, ?);";
		jdbctemplate.update(sql, category.getCategory_name(), category.getDepreciation_years());
	}
	
	public void deleteCategory(int category_id) {
		String sql = "DELETE FROM category WHERE categoryid = ?;";
		jdbctemplate.update(sql, category_id);
	}
	
	public Category findCategoryById(int category_id) {
		String sql = "SELECT * FROM category WHERE categoryid = ?;";
		return jdbctemplate.queryForObject(sql, new Object [] {category_id}, new CategoryMapper());
	}
}
