package com.spring.join.jwt.controller;

import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.join.jwt.model.BookCategory;

import com.spring.join.jwt.repositories.CategoryRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	final private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@GetMapping(path = "/categories")
	public ResponseEntity<Page<BookCategory>> getAllCategoryByActivation(Pageable pageable) throws URISyntaxException {
		Page<BookCategory> page = categoryRepository.findByActive(pageable);
		return new ResponseEntity<Page<BookCategory>>(page, HttpStatus.OK);
	}
	
	@GetMapping(path = "/category/{id}")
	public ResponseEntity<Page<BookCategory>> getAllBookByCategory(@PathVariable("id") int id,Pageable pageable) throws URISyntaxException {
		Page<BookCategory> page = categoryRepository.findById(id,pageable);
		return new ResponseEntity<Page<BookCategory>>(page, HttpStatus.OK);
	}

	@GetMapping(path = "/category/false")
	public ResponseEntity<Page<BookCategory>> getAllBookByInactive(Pageable pageable) throws URISyntaxException {
		Page<BookCategory> page = categoryRepository.findByInactive(pageable);
		return new ResponseEntity<Page<BookCategory>>(page, HttpStatus.OK);
	}

	
	@PostMapping(path = "/addcategory")
	public BookCategory create(@RequestParam Map<String, String> body) {
		
		String categoryName = body.get("categoryName").toString();
		boolean active = true;
		BookCategory bookCategory = new BookCategory(categoryName,active);
		
		return categoryRepository.save(bookCategory);
		
	}
	
	@PutMapping("/category/delete/{id}")
	public void activeOrInActive(@PathVariable String id, @RequestBody Map<String, Boolean> body) {
		int catId = Integer.parseInt(id);
		boolean active = body.get("active");
		categoryRepository.updateStatus(active, catId);

	}
	
}
