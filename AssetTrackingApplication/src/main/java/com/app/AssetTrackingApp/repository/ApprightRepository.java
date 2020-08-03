package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.AssetTrackingApp.mapper.ApprightMapper;
import com.app.AssetTrackingApp.model.Appright;

@Repository
public class ApprightRepository {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Appright> listAllRights(){
		
		List<Appright> rights = new ArrayList<Appright>();
		String sql = "SELECT * FROM appright;";
		rights.addAll(jdbctemplate.query(sql, new ApprightMapper()));
		return rights;
	}
	
	public Appright findRightById(int rightid) {
		String sql = "SELECT * FROM appright WHERE rightid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {rightid}, new ApprightMapper());
	}
	
	public void addRight(Appright right) {
		String sql = "INSERT INTO appright (right_description, right_code) VALUES (?, ?);";
		jdbctemplate.update(sql, right.getRight_description(), right.getRight_code());
	}
	
	public void deleteRight(int rightid) {
		String sql = "DELETE FROM appright WHERE rightid = ?;";
		jdbctemplate.update(sql, rightid);
	}
}
