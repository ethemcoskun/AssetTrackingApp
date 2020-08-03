package com.app.AssetTrackingApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.Validate;

import com.app.AssetTrackingApp.model.Vendor;
import com.app.AssetTrackingApp.repository.VendorRepository;

@Controller(value = "/")
public class VendorController {

	@Autowired
	VendorRepository vendorrepository;
	
	@GetMapping(value = "/allvendors")	
	public ModelAndView listAllVendors(ModelAndView mv) {
		
		mv.addObject("all_vendor_info", vendorrepository.getAllVendors());
		mv.setViewName("vendors");
		return mv;
	}
	
	@GetMapping(value = "/newvendor")
	public ModelAndView newVendor(ModelAndView mv) {
		
		Vendor newVendor = new Vendor();
		mv.addObject("new_vendor", newVendor);
		mv.setViewName("newvendor");
		
		return mv;
	}
	
	@PostMapping(value = "/newvendor")
	public ModelAndView addNewVendor(@Valid @ModelAttribute("new_vendor") Vendor vendor, BindingResult result) {
		
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView();			
			mv.setViewName("newvendor");
			return mv;
		}
		vendorrepository.addNewVendor(vendor);		 
		return new ModelAndView("redirect:/allvendors");
	}
	
	@GetMapping(value = "/deletevendor/{vendor_id}")
	public ModelAndView deleteVendor(@PathVariable("vendor_id") int vendor_id, @Valid Vendor vendor, BindingResult result) {
		
		vendor = vendorrepository.findVendorById(vendor_id);
		
		try {	
			vendorrepository.deleteVendor(vendor_id);
			}
		catch (Exception e) {
			// TODO: handle exception 
			ModelAndView mv = new ModelAndView();
			mv.addObject("this_vendor", vendor);
			mv.setViewName("cannotdeletevendor");
			return mv;
	 
		}
			return new ModelAndView("redirect:/allvendors");
	}
	
	@GetMapping(value = "/vendordetails/{vendor_id}")
	public ModelAndView getVendorDetails(@PathVariable("vendor_id") int vendor_id) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vendor_info", vendorrepository.findVendorById(vendor_id));
		mv.setViewName("vendordetails");
		return mv;
	}
	
	@GetMapping("/cannotdelete")
	public ModelAndView cannotDelete(ModelAndView mv) {
		mv.setViewName("cannotdeletevendor");
		return mv;
	}
	
	@GetMapping(value = "/updatevendor/{vendor_id}")
	public ModelAndView updateVendorForm(ModelAndView mv, @PathVariable("vendor_id") int vendor_id) {
		
		Vendor this_vendor = vendorrepository.findVendorById(vendor_id);
		mv.addObject("this_vendor", this_vendor);
		mv.setViewName("updatevendor");
		
		return mv;
	}
	
	@PostMapping(value = "/updatevendor/{vendor_id}")
	public ModelAndView updateVendor(@Valid @ModelAttribute("this_vendor") Vendor vendor, BindingResult result, @PathVariable("vendor_id") int vendor_id) {
		
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView();			
			mv.setViewName("updatevendor");
			return mv;
		}
		vendorrepository.updateVendor(vendor);		 
		return new ModelAndView("redirect:/allvendors");
	}
}
