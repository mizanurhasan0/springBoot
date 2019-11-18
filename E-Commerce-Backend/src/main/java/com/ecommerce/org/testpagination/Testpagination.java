package com.ecommerce.org.testpagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.org.dto.Products;

public interface Testpagination extends JpaRepository<Products, Long> {

//	Page<Products> findByCategoryId(int id,Pageable pageable);
}
