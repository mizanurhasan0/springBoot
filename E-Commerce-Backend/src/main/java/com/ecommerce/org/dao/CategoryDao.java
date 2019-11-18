package com.ecommerce.org.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.org.dto.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
	
	List<Category> findByActive(int active);
	
}
