package com.example.image.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.image.model.ImageModel;

@Service
public interface ImageRepository extends
JpaRepository<ImageModel, Long> {
	
	@Query(value = "SELECT * FROM image_model ",nativeQuery = true)
    Page<ImageModel> findAll(Pageable pageable);

}
