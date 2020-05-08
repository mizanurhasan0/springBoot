package com.ecommerce.org.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ecommerce.org.model.ImageModel;
import com.ecommerce.org.model.ImageModel2;

@Service
public interface ImageRepository2 extends JpaRepository<ImageModel2, Long> {
	
	@Query(value = "SELECT * FROM image_model2 ",nativeQuery = true)
    Page<ImageModel2> findAll(Pageable pageable);

}
