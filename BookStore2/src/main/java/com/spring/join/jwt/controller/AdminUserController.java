package com.spring.join.jwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.join.jwt.exception.ValidationException;
import com.spring.join.jwt.model.AdminUser;
import com.spring.join.jwt.repositories.AdminUserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminUserController {


    final private AdminUserRepository adminUserRepository;

//    private HashData hashData = new HashData();

    public AdminUserController(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }


    @PostMapping("/admin/user")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        String email = body.get("email");
        if (adminUserRepository.existsByEmail(email)){

            throw new ValidationException("Email already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        
        String mobile = body.get("mobile");
        adminUserRepository.save(new AdminUser(username, encodedPassword, email,mobile));
        return true;
    }

}


