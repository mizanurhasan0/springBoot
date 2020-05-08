package com.spring.join.jwt.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.join.jwt.model.Book;



public interface BookRepository extends JpaRepository<Book,Long> {
//    @Query(value = "SELECT * FROM blog b WHERE b.active=true order by b.id DESC",nativeQuery = true)
//    List<Blog> findByActive();
//    
//    @Modifying
    @Query(value = "UPDATE tbl_book SET active = ?1 WHERE id =?2",nativeQuery = true)
    void updateStatus(boolean active, long id);
//    
    @Query(value = "SELECT * FROM tbl_book b WHERE b.active=true order by b.id DESC",nativeQuery = true)
    Page<Book> findByActive(Pageable pageable);
    
    @Query(value = "SELECT * FROM tbl_book b WHERE b.active=true and category_id=?1 order by b.id DESC",nativeQuery = true)
    Page<Book> findByCategory(int category,Pageable pageable);
    
    @Query(value = "SELECT * FROM tbl_book b WHERE b.active=false order by b.id DESC",nativeQuery = true)
    Page<Book> findByInactive(Pageable pageable);
    
    //custom query to search to blog post by title or content
   List<Book> findByNameOrDescription(String text, String textAgain);
//    
//    @Query(value = "SELECT * FROM book  WHERE active=true and userid=?1 order by id DESC",nativeQuery = true)
//    Page<Book> findByUserid(String userid,Pageable pageable);
//    
//    
//    @Query(value = "SELECT * FROM blog WHERE active=true and useremail=1? order by id DESC",nativeQuery = true)
//    Page<Blog> singleUserPosts(String useremail,Pageable pageable);
    
    //Page<Blog> findByUserid(String userid,Pageable pageable);
   @Query(value = "SELECT * FROM tbl_book b WHERE b.active=true and  b.name like %?1%",nativeQuery = true)
   Page<Book> findByName(String name,Pageable pageable);
   
   @Query(value = "SELECT * FROM tbl_book b WHERE b.active=true and b.category_id=?1 and  b.name like %?2%",nativeQuery = true)
   Page<Book> searchByCategory(int category,String name,Pageable pageable);
   
   @Query(value = "SELECT * FROM tbl_book b WHERE  b.sku=?1",nativeQuery = true)
   String findBySku(String sku);
    
}

