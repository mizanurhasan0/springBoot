package com.ecommerce.org.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.ecommerce.org.dto.Products;


public interface ProductsDao extends JpaRepository<Products, Long>{
	
    List<Products> findByActive(boolean active);
    
    @Query(value = "from products where active=true and category_id=?1")
	List<Products> findByCategoryId(int categoryId);
    
   
//    @Query(countQuery = "SELECT * products p WHERE p.active=true and p.category_id=?1",nativeQuery = true)
//	Page<Products> findByCategoryIdPages(int categoryId, Pageable pageable);
//    
	@Query(value = "select * from products where active=true order by id DESC limit ?1",nativeQuery = true)
	List<Products> getLatestActiveProducts(int count);
	
	
}
