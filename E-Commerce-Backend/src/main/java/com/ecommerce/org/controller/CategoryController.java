package com.ecommerce.org.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.org.doaImp.CategoryDaoImp;
import com.ecommerce.org.dto.Category;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryDaoImp categoryDaoImp;
	
//	@GetMapping(value = "/categorys")
//	public List<Category> getAllCategory(){
//		return categoryDaoImp.getAllCategory();
//	}
	
	@GetMapping(path = "/categorys/active")
	public List<Category> getAllCategorybyActive(){
		return categoryDaoImp.getActiveCategory(0);
	}
	
	@PostMapping(path = "/categorys/add")
	public void addCategory(@RequestBody Category  category) {
		
		String name=category.getName();
		 String description=category.getDescription();
		
		 String imageUrl=category.getImage();
		 //int active=category.isActive();;
		
		categoryDaoImp.addCategory(new Category(name,description,imageUrl,0));
	}
}
