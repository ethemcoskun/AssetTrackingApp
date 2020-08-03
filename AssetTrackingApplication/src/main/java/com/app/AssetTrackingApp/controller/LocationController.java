package com.app.AssetTrackingApp.controller;

import java.util.List;

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

import com.app.AssetTrackingApp.model.Location;
import com.app.AssetTrackingApp.model.Site;
import com.app.AssetTrackingApp.repository.AssetRepository;
import com.app.AssetTrackingApp.repository.LocationRepository;
import com.app.AssetTrackingApp.repository.SiteRepository;

@Controller
@RequestMapping(value = "/")
public class LocationController {

	@Autowired
	LocationRepository locationRepository;
	@Autowired
	SiteRepository siteRepository;
	@Autowired
	AssetRepository assetRepository;
	

	@GetMapping(value = "/locations")	
	public ModelAndView selectSite(ModelAndView mv) {
		mv.addObject("all_site_info", siteRepository.getAllSites());
		mv.setViewName("locations");
		return mv;
	}
	
	@GetMapping(value = "/sitelocations/{site_id}")	
	public ModelAndView getLocations(@PathVariable("site_id") int site_id) {
		List<Location> siteLocations = locationRepository.getLocationsBySiteId(site_id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("all_location_info", siteLocations);
		mv.addObject("this_site", siteRepository.findBySiteId(site_id));
		mv.setViewName("sitelocations");
		return mv;
	}

	@GetMapping(value = "/newlocation/{site_id}")
	public ModelAndView newLocation(@PathVariable("site_id") int site_id, ModelAndView mv) {

		mv.addObject("new_location", new Location());
		mv.addObject("this_site", siteRepository.findBySiteId(site_id));
		mv.setViewName("newlocation");
		return mv;
	}
	
	@PostMapping(value = "/newlocation/{site_id}")
	public ModelAndView addNewLocation(@Valid @ModelAttribute("new_location") Location location, BindingResult result, @PathVariable("site_id") int site_id) { 
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) {
			mv.addObject("this_site", siteRepository.findBySiteId(site_id));
			mv.setViewName("newlocation");
			return mv;
		}
		
		locationRepository.addLocation(location);
		return new ModelAndView("redirect:/sitelocations/" + location.getLocation_siteId());
	}
	
	@GetMapping("/deletelocation/{location_id}")
	public ModelAndView deleteLocation(@PathVariable("location_id") int location_id, Location location, Site site){
		site = locationRepository.getSiteByLocationId(location_id);
		location = locationRepository.findLocationById(location_id);
		try {
		locationRepository.deleteLocation(location_id, site.getSite_id());
		}
		catch (Exception e) {
			// TODO: handle exception
			ModelAndView mv = new ModelAndView();
			mv.addObject("this_site", site);
			mv.addObject("this_location", location);
			mv.setViewName("cannotdeletelocation");
			return mv;
		}
		return new ModelAndView("redirect:/sitelocations/" + site.getSite_id());			
	}
	
}
