package com.spring.join.jwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.join.jwt.model.AdminUser;


@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Integer> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    AdminUser findByUsername(String username);
    
    //UserInfo findByEmail(String email);
    //@Query(value = "SELECT * FROM userinfo u WHERE u.active=true and u.email= ?1 and order by u.id DESC",nativeQuery = true)
    AdminUser findByEmail(String email);
    
    @Query(value = "SELECT * FROM admin u WHERE u.active=true",nativeQuery = true)
    List<AdminUser> findByActive();

}

