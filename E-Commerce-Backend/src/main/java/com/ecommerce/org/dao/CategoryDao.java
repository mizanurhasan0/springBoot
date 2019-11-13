package com.ecommerce.org.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.org.dto.Category;

public interface CategoryDao {
	List<Category> getAllCategory();
	
}
