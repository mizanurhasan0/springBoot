package com.blog.jwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.jwt.exceptions.ValidationException;
import com.blog.jwt.model.UserInfo;
import com.blog.jwt.repositories.UserInfoRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserInfoController {


    final private UserInfoRepository userInfoRepository;

//    private HashData hashData = new HashData();

    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        String email = body.get("email");
        if (userInfoRepository.existsByEmail(email)){

            throw new ValidationException("Email already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        
        String mobile = body.get("mobile");
        userInfoRepository.save(new UserInfo(username, encodedPassword, email,mobile));
        return true;
    }

}


