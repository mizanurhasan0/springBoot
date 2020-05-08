package com.spring.join.jwt.model;


import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String mobile;
    private String role=userRole();
    @JsonIgnore
    private boolean active=true;

    public String userRole() {
    	String originalInput = "user";
    	String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
    	  return encodedString;
    }
    
    public UserInfo() {
    }


    public UserInfo(String username, String password, String email,String mobile,boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile=mobile;
        this.active=active;
    }
    
    public UserInfo(String username, String password, String email,String mobile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile=mobile;
    }
    
    public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", mobile=" + mobile + ", active=" + active + "]";
	}

   
}


