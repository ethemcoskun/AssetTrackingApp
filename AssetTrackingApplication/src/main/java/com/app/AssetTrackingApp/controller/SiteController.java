package com.app.AssetTrackingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.AssetTrackingApp.model.Site;
import com.app.AssetTrackingApp.repository.SiteRepository;

@Controller
@RequestMapping(value = "/")
public class SiteController {
	
	@Autowired
	SiteRepository siteRepository;
	
	@GetMapping("/allsites")
	public ModelAndView listAllSites(ModelAndView mv) {
		
		mv.addObject("all_site_info", siteRepository.getAllSites());
		mv.setViewName("sites");
		return mv;
	}
	
	@GetMapping("/newsite")
	public ModelAndView newSite(ModelAndView mv) {
		mv.addObject("new_site", new Site());
		mv.setViewName("newsite");
		return mv;
	}
	
	@PostMapping("/newsite")
	public ModelAndView addNewSite(@ModelAttribute("new_site") Site site) {
		siteRepository.addSite(site);
		return new ModelAndView("redirect:/allsites");
	}
	
	@GetMapping("/deletesite")
	public ModelAndView deleteSite(@RequestParam("site_id") int site_id, Site site) {
		
		site = siteRepository.findBySiteId(site_id);
		
		try {
			siteRepository.deleteSite(site_id);
		}
		catch (Exception e) {
			// TODO: handle exception
			ModelAndView mv = new ModelAndView();
			mv.addObject("this_site", site);
			mv.setViewName("cannotdeletesite");
			return mv;
		}
		
		return new ModelAndView("redirect:/allsites");
	}
}
