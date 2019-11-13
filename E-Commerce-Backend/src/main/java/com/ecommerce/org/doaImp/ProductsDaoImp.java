package com.ecommerce.org.doaImp;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.org.dao.ProductsDao;
import com.ecommerce.org.dto.Products;

@Service
@Transactional
public class ProductsDaoImp {

	@Autowired
	private ProductsDao productsDao;
	
	///----------------------***********-------------------------

//	public Page<Products> listActiveProductsByCategoryPage( int page, int size, int categoryId){
//		//productsDao.findByCategoryIdPages(categoryId);
//		Pageable pageable = PageRequest.of(page, size);
//		return productsDao.findByCategoryIdPages(categoryId,pageable);
//	}
//	
	///-----------------------------**---------------------------------------------------
	public List<Products> getAllProducts(){
		return productsDao.findAll();
	}
	
	public Products getProductsById(Long id) {
		return productsDao.findById(id).get();
	}
	
	public void deleteProductById(Long id) {
		 productsDao.deleteById(id);
	}
	
	public ResponseEntity<Products> updateProduct(Products product,long id) {
		productsDao.deleteById(id);
		productsDao.save(product);
		return new ResponseEntity<Products>(product,HttpStatus.OK);
	}
	
	public ResponseEntity<Void> addProduct(Products product) {
		Products pro=productsDao.save(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	public List<Products> listActiveProducts(boolean active){
		return productsDao.findByActive(active);
	}
	
	public List<Products> listActiveProductsByCategory(int categoryId){
		return productsDao.findByCategoryId(categoryId);
	}
	

	
	public List<Products> getLatestActiveProducts(int count){
		return productsDao.getLatestActiveProducts(count);
	}
	
}
