package com.app.AssetTrackingApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.AssetTrackingApp.model.Appright;
import com.app.AssetTrackingApp.model.Role;
import com.app.AssetTrackingApp.repository.ApprightRepository;
import com.app.AssetTrackingApp.repository.RoleRepository;

@Controller
@RequestMapping("/")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ApprightRepository apprightRepository;
	
	@GetMapping(value = "/allroles")
	public ModelAndView listAllRoles(ModelAndView mv) {
		
		mv.addObject("all_role_info", roleRepository.listAllRoles());
		mv.setViewName("allroles");
		
		return mv;
	}
	
	@GetMapping(value = "/newrole")
	public ModelAndView newRoleForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("role", new Role());
		mv.addObject("apprightMap", getApprightsById());
		mv.setViewName("newrole");
		System.out.println("here1");
		return mv;
	}
	
	@PostMapping(value = "/newrole")
	public ModelAndView newRole(@Valid @ModelAttribute("role") Role role, BindingResult result, @RequestParam("rights") int[] rights) {
		 
		if(result.hasErrors()) {			
			ModelAndView mv = new ModelAndView();
			mv.addObject("apprightMap", getApprightsById());
			mv.setViewName("newrole");
			return mv;
		}
		roleRepository.addRole(role);	
		bindApprightsToRole(rights, roleRepository.findRoleByRolename(role.getRolename()));
		
		return new ModelAndView("redirect:/allroles");
		
	}
	
	@GetMapping(value = "roledetails/{roleid}")
	public ModelAndView roleDetails(@PathVariable("roleid") int roleid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("role_info", roleRepository.findRoleById(roleid));
		mv.addObject("role_rights", getRoleApprights(roleid));
		mv.setViewName("roledetails");
		return mv;
	}
	
	@GetMapping(value = "/deleterole")
	public ModelAndView deleteRole(@RequestParam("roleid") int roleid) {
		roleRepository.deleteRole(roleid);
		return new ModelAndView("redirect:/allroles");
	}
	
	public ArrayList<Appright> getRoleApprights(int roleid) {
		ArrayList<Appright> roleApprights = new ArrayList<>();
		for(Integer id : roleRepository.getApprightIdsByRoleId(roleid)) {
			roleApprights.add(apprightRepository.findRightById(id));
		}
		return roleApprights;
	}
	
	public void bindApprightsToRole(int[] rights, Role role) {
		System.out.println("rolename" + role.getRolename() + "roleid:" + role.getRoleid());
		for(int rightid : rights) { 
			roleRepository.addApprightToRole(role.getRoleid(), rightid);
		}
	}
	
	public HashMap<Integer, String> getApprightsById(){		
		
		HashMap<Integer, String> apprightsMap = new HashMap<Integer, String>();
		for(Appright right : apprightRepository.listAllRights()) {
			apprightsMap.put(right.getRightid(), right.getRight_description());		
		}
		return apprightsMap;
	}
}
