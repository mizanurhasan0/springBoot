package com.spring.join.jwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.join.jwt.model.UserInfo;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    UserInfo findByUsername(String username);
    
   
    
    @Query(value = "SELECT * FROM userinfo where email= ?1",nativeQuery = true)
    UserInfo findByEmail(String email);
    
    @Query(value = "SELECT * FROM userinfo u WHERE u.active=true",nativeQuery = true)
    List<UserInfo> findByActive();

}

