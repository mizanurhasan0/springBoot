package com.blog.jwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.blog.jwt.model.Feedback;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    // custom query to search to blog post by title or content
    //List<Feedback> findByTitleContainingOrContentContaining(String text, String textAgain);
    
    @Query(value = "SELECT * FROM feedback b WHERE b.active=true order by b.id DESC",nativeQuery = true)
    List<Feedback> findByActive();

}

