package com.app.AssetTrackingApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.AssetTrackingApp.model.Category;
import com.app.AssetTrackingApp.repository.CategoryRepository;

@Controller
@RequestMapping(value="/")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping(value="/allcategories")
	public ModelAndView listAllCategories(ModelAndView mv) {
		
		mv.addObject("all_category_info", categoryRepository.getAllCategories());
		mv.setViewName("categories");
		
		return mv;
	}
	
	@GetMapping(value="/newcategory")
	public ModelAndView newCategory(Category category, ModelAndView mv) {
		
		mv.addObject("cat", category);
		mv.setViewName("newcategory");
		return mv;
	}
	
	@PostMapping(value="/newcategory")
	public ModelAndView addCategory(@Valid @ModelAttribute("cat") Category category, BindingResult result) { 
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) {
//			System.out.println("category error");
//			for(ObjectError o : result.getAllErrors()) {
//				System.out.println(o.getDefaultMessage());
//			}
			mv.setViewName("newcategory");
			return mv;
		}
		
		categoryRepository.addNewCategory(category); 
	    mv.setViewName("redirect:/allcategories");
	    return mv;
	}
	
	@GetMapping(value = "/deletecategory")
	public ModelAndView deleteCategory(@RequestParam(name = "category_id") int category_id, Category category ) {
		category = categoryRepository.findCategoryById(category_id);
		
		try {
			categoryRepository.deleteCategory(category_id);

		}
		catch (Exception e) {
			// TODO: handle exception
			ModelAndView mv = new ModelAndView();
			mv.addObject("this_category", category);
			mv.setViewName("cannotdeletecategory");
			return mv;
		}
		
		
		return new ModelAndView("redirect:/allcategories");
	}
	

}
