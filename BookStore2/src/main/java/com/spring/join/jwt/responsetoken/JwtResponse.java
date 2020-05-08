package com.spring.join.jwt.responsetoken;


import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;
    private final String email;
    private final int id;

    public JwtResponse(String jwttoken,String email,int id) {

        this.jwttoken = jwttoken;
        this.email=email;
        this.id=id;
    }

    public String getToken() {

        return this.jwttoken;

    }
    
    public String getEmail() {

        return this.email;

    }
    public int getId() {

        return this.id;

    }

}

