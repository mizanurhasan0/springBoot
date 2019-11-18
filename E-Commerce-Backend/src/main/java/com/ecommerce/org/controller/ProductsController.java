package com.ecommerce.org.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.ecommerce.org.dao.ProductsDao;
import com.ecommerce.org.doaImp.ProductsDaoImp;
import com.ecommerce.org.dto.Products;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductsController {

	@Autowired
	private ProductsDaoImp productService;

	@GetMapping(path = "/products/catagory/{id}")
	 public ResponseEntity<Page<Products>> getAllProductsByCategory( Pageable pageable,@PathVariable int id)
		        throws URISyntaxException {           
		        Page<Products> page = productService.listActiveProductsByCategoryPage( pageable,id);
		        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/students/classroom");
		        return new ResponseEntity<Page<Products>>(page, HttpStatus.OK);
		    }
	
	@GetMapping(path = "/products/activated/{active}")
	 public ResponseEntity<Page<Products>> getAllProductsByActivation(Pageable pageable,@PathVariable int active)
		        throws URISyntaxException {           
		        Page<Products> page = productService.listActiveProducts(pageable,active);
		        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/students/classroom");
		        return new ResponseEntity<Page<Products>>(page, HttpStatus.OK);
		    }
//	
//	@GetMapping("/products/all")
//	public List<Products> getAllProducts(){
//		return productService.getAllProducts();
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
	public ResponseEntity<Void> addProduct(@RequestBody Products product){
		String code=product.getCode();
		String name=product.getName();
		String brand=product.getBrand();
		String description=product.getDescription();
		int unitprice=product.getUnitprice();
		int quantity=product.getQuantity();
		int active=product.getActive();
		int categoryid=product.getCategoryid();
		int supplierid=product.getSupplierid();
		int purchases=product.getPurchases();
		int views=product.getViews();
		return productService.addProduct(new Products
				(code,name,brand,description,unitprice,quantity,active,categoryid,supplierid,purchases,views)); 
	}
	
	
	@PutMapping(path = "/products/update/{id}")
	public ResponseEntity<Products> updateProduct(@RequestParam Long id,Products product){
		return productService.updateProduct(product,id); 
	}
}
