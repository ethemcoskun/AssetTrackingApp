package com.app.AssetTrackingApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.AssetTrackingApp.model.Appright;
import com.app.AssetTrackingApp.repository.ApprightRepository; 

@Controller
@RequestMapping("/")
public class ApprightController {

	@Autowired
	ApprightRepository rightRepository;
	
	@GetMapping(value = "/allrights")
	public ModelAndView listAllRights(ModelAndView mv) {
		
		mv.addObject("all_right_info", rightRepository.listAllRights());
		mv.setViewName("allrights");
		
		return mv;
	}
	
	@GetMapping(value = "/newright")
	public ModelAndView newRightForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("right", new Appright());
		mv.setViewName("newright"); 
		return mv;
	}
	
	@PostMapping(value = "/newright")
	public ModelAndView newRole(@Valid @ModelAttribute("right") Appright right, BindingResult result) {
		 
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("newright");
			return mv;
		}
		rightRepository.addRight(right);
		return new ModelAndView("redirect:/allrights");
		
	}
	
	@GetMapping(value = "/deleteright")
	public ModelAndView deleteRole(@RequestParam("rightid") int rightid) {
		rightRepository.deleteRight(rightid);
		return new ModelAndView("redirect:/allrights");
	}
}
