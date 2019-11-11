package com.tutorial.mysql.basicAuth;

public class BasicAuthModel {

	
	private String message;

	public BasicAuthModel(String msg) {
		// TODO Auto-generated constructor stub
		this.message=msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	
}
