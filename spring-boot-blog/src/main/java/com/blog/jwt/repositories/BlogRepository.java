package com.blog.jwt.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.jwt.model.Blog;


@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    // custom query to search to blog post by title or content
    List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);

    @Query(value = "SELECT * FROM blog b WHERE b.active=true order by b.id DESC",nativeQuery = true)
    List<Blog> findByActive();
    
    @Modifying
    @Query(value = "UPDATE blog SET active = ?1 WHERE id =?2",nativeQuery = true)
    void updateStatus(boolean active, long id);
    
    @Query(value = "SELECT * FROM blog b WHERE b.active=true order by b.id DESC",nativeQuery = true)
    Page<Blog> findByActive(Pageable pageable);
    
    @Query(value = "SELECT * FROM blog WHERE active=true and userid=?1 order by id DESC",nativeQuery = true)
    Page<Blog> singleUserPosts(int userId,Pageable pageable);
    
}

