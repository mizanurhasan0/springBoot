package com.spring.join.jwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.join.jwt.exception.ValidationException;
import com.spring.join.jwt.model.UserInfo;
import com.spring.join.jwt.repositories.UserInfoRepository;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserInfoController {


    final private UserInfoRepository userInfoRepository;


    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public void textEncode()  {
    	String originalInput = "user";
    	String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
    	System.out.println(encodedString+" :encodedString");
    	byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    	String decodedString = new String(decodedBytes);
    	System.out.println(decodedString+" :decodedString");
    }
  
    @GetMapping("/getUsers")
    public List<UserInfo> getUsers() {
    	return userInfoRepository.findAll();
    }
    
    @GetMapping("/getEmail")
    public UserInfo getUserInfo(@RequestBody Map<String, String> body) {
    	return userInfoRepository.findByEmail(body.get("email"));
    }

    @PostMapping("/addUser")
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


