package com.spring.join.jwt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.join.jwt.model.BookCategory;

public interface CategoryRepository extends JpaRepository<BookCategory, Long>{

	  @Query(value = "UPDATE tbl_category SET active = ?1 WHERE id =?2",nativeQuery = true)
	  void updateStatus(boolean active, long id);
	//    
	    @Query(value = "SELECT * FROM tbl_category b WHERE b.active=0 order by b.id DESC",nativeQuery = true)
	    Page<BookCategory> findByActive(Pageable pageable);
	    
	    @Query(value = "SELECT * FROM tbl_category b WHERE b.active=true and id=?1 order by b.id DESC",nativeQuery = true)
	    Page<BookCategory> findById(int id,Pageable pageable);
	    
	    @Query(value = "SELECT * FROM tbl_category b WHERE b.active=false order by b.id DESC",nativeQuery = true)
	    Page<BookCategory> findByInactive(Pageable pageable);
	    
	    //custom query to search to blog post by title or content
	   List<BookCategory> findByCategoryName(String text);
	   
	   @Query(value = "SELECT id FROM tbl_category b where b.active=0 order by b.id ASC LIMIT 1 ",nativeQuery = true)
	   int GetFirstId() ;
	   
	   @Query(value = "SELECT id FROM tbl_category b where b.active=0 order by b.id ASC",nativeQuery = true)
	   List<Integer> GetAllId() ;
}
