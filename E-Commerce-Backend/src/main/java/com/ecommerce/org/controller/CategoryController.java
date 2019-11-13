package com.ecommerce.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.org.doaImp.CategoryDaoImp;
import com.ecommerce.org.dto.Category;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryDaoImp categoryDaoImp;
	
	@GetMapping(value = "/categorys")
	public List<Category> getAllCategory(){
		return categoryDaoImp.getAllCategory();
	}
}
