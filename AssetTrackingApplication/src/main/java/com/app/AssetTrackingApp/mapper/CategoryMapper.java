package com.app.AssetTrackingApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.AssetTrackingApp.model.Category;

public class CategoryMapper implements RowMapper<Category>{

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		Category category = new Category();
		category.setCategory_id(rs.getInt("categoryid"));
		category.setCategory_name(rs.getString("categoryname"));
		category.setDepreciation_years(rs.getInt("depreciationyears"));
		
		return category;
				
	}

}
