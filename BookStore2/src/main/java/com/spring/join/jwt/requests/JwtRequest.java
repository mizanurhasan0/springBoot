package com.spring.join.jwt.requests;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String email;

    private String password;

    public JwtRequest()

    {

    }

    public JwtRequest(String email, String password) {

        this.setEmail(email);

        this.setPassword(password);

    }
//
//    public String getUsername() {
//
//        return this.email;
//
//    }
//
//    public void setUsername(String username) {
//
//        this.email = username;
//
//    }

    public String getPassword() {

        return this.password;

    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {

        this.password = password;

    }

}


