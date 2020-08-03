package com.app.AssetTrackingApp.repository;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
import com.app.AssetTrackingApp.mapper.RoleMapper;
import com.app.AssetTrackingApp.model.Appright;
import com.app.AssetTrackingApp.model.Role;
import com.sun.tools.javac.code.Attribute.Array;

@Repository
public class RoleRepository {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Role> listAllRoles(){
		List<Role> roles = new ArrayList<>();
		String sql = "SELECT * FROM role;";
		roles.addAll(jdbctemplate.query(sql, new RoleMapper()));
		
		return roles;
	}
	
	public Role findRoleById(int roleid) {
		String sql = "SELECT * FROM role WHERE roleid = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {roleid}, new RoleMapper()); 
	}
	
	public Role findRoleByRolename(String rolename) {
		String sql = "SELECT * FROM role WHERE rolename = ?;";
		return jdbctemplate.queryForObject(sql, new Object[] {rolename}, new RoleMapper());
	}
	
	public void addRole(Role role) {
		String sql = "INSERT INTO role (rolename) VALUES (?);";
		jdbctemplate.update(sql, role.getRolename());
	}
	
	public void deleteRole(int roleid) {
		String sql_remove_rights = "DELETE FROM rolerights WHERE roleid = ?;";
		jdbctemplate.update(sql_remove_rights, roleid);
		
		String sql_delete_role = "DELETE FROM role WHERE roleid = ?;";
		jdbctemplate.update(sql_delete_role, roleid);
	}
	
	public void addApprightToRole(int roleid, int rightid) {
		String sql = "INSERT INTO rolerights (roleid, rightid) VALUES (?, ?);";
		System.out.println(roleid + ">>" + rightid);
		jdbctemplate.update(sql, roleid, rightid);
	}
	
	public ArrayList<Integer> getApprightIdsByRoleId(int roleid){
		String sql = "SELECT rightid FROM rolerights WHERE roleid = " + roleid + ";";
		ArrayList<Integer> rightIds = (ArrayList<Integer>) jdbctemplate.queryForList(sql, Integer.class);
		return rightIds;
	}
}
