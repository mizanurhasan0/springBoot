package com.ecommerce.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.org.dao.ProductsDao;
import com.ecommerce.org.doaImp.ProductsDaoImp;
import com.ecommerce.org.dto.Products;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductsController {

	//------------*********--------------------------
//	@Autowired
//	private ProductsDao daoService;
//	
	@Autowired
	private ProductsDaoImp productService;
	
//	@GetMapping("/page/show/{id}")
//	public Page<Products> listActiveProductsByCategoryPage(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size,@PathVariable int categoryId){
//		
//		return listActiveProductsByCategoryPage(page,size,categoryId );
//	}
//	
	//------------*********--------------------------
	
	
	
	@GetMapping(path = "/products")
	public List<Products> getAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping(path = "/products/active")
	public List<Products> getActiveProduct(){
		return productService.listActiveProducts(true);
	}
	
//	@GetMapping(path = "/products/cat/{id}")
//	public List<Products> findByCategoryId(@PathVariable int id){
//		return productService.listActiveProductsByCategory(id);
//	}

	
	
	@GetMapping(path = "/products/lim/{count}")
	public List<Products> findByLimtation(@PathVariable int count){
		return productService.getLatestActiveProducts(count);
	}
	
	@GetMapping(path = "/products/{id}")
	public Products addProduct(@PathVariable Long id){
		return productService.getProductsById(id); 
	}
	
	@PostMapping(path = "/products/add")
	public ResponseEntity<Void> addProduct(Products product){
		return productService.addProduct(product); 
	}
	
	@PutMapping(path = "/products/update/{id}")
	public ResponseEntity<Products> updateProduct(@RequestParam Long id,Products product){
		return productService.updateProduct(product,id); 
	}
}
