package com.app.AssetTrackingApp.controller;

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
import com.app.AssetTrackingApp.model.User;
import com.app.AssetTrackingApp.repository.ApprightRepository;
import com.app.AssetTrackingApp.repository.RoleRepository;
import com.app.AssetTrackingApp.repository.UserRepository;

@Controller
@RequestMapping(value = "/")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ApprightRepository apprightRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping(value = "/allusers")
	public ModelAndView listAllUsers(ModelAndView mv) {
		
		mv.addObject("all_user_info", userRepository.getAllUsers());
		mv.addObject("roleMap", getRoleNameById());
		mv.setViewName("allusers");
		
		return mv;
		
	}
	
	@GetMapping(value = "/newuser")
	public ModelAndView newUserForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("roleMap", getRoleNameById());
		mv.addObject("user", new User());
		mv.setViewName("newuser");
		return mv;
	}

	@PostMapping(value = "/newuser")
	public ModelAndView newUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView();
			mv.addObject("roleMap", getRoleNameById());
			mv.setViewName("newuser");
			return mv;
		}
		
		userRepository.addUser(user);
		return new ModelAndView("redirect:/allusers");
	}
	
	@GetMapping(value = "/userdetails/{userid}")
	public ModelAndView getUserDetails(@PathVariable("userid") int userid) {
		
		ModelAndView mv = new ModelAndView();
		User user = userRepository.findUserById(userid);
		mv.addObject("user_info", user);
		mv.addObject("role", roleRepository.findRoleById(user.getRoleid()));
		mv.setViewName("userdetails");
		return mv;
	}
	
	@GetMapping(value = "/deleteuser")
	public ModelAndView deleteUser(@RequestParam("userid") int userid) {		 
		userRepository.deleteUser(userid);
		return new ModelAndView("redirect:/allusers");
	}	
	
	@GetMapping(value = "/updateuser/{userid}")
	public ModelAndView updateUserForm(@PathVariable("userid") int userid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("roleMap", getRoleNameById());
		mv.addObject("this_user", userRepository.findUserById(userid));
		mv.setViewName("updateuser");
		return mv;
	}
	
	@PostMapping(value = "/updateuser/{userid}")
	public ModelAndView updateUser(@Valid @ModelAttribute("this_user") User user, BindingResult result, @PathVariable("userid") int userid) {
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView();
			mv.addObject("roleMap", getRoleNameById());
			mv.setViewName("updateuser");
			return mv;
		}
		userRepository.updateUser(user);
		return new ModelAndView("redirect:/allusers");
	}
	
	private HashMap<Integer, String> getApprightNameById(){ 
		HashMap<Integer, String> rightsById = new HashMap<Integer, String>();
		for(Appright right : apprightRepository.listAllRights()) {
			rightsById.put(right.getRightid(), right.getRight_description());
		}
		return rightsById;
	}
	
	private HashMap<Integer, String> getRoleNameById(){ 
		HashMap<Integer, String> rolesById = new HashMap<Integer, String>();
		for(Role role : roleRepository.listAllRoles()) { 
			rolesById.put(role.getRoleid(), role.getRolename());
		}
		return rolesById;
	}
	
	

	
}
