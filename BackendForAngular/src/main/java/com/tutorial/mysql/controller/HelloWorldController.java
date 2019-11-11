package com.tutorial.mysql.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorldController {
	
	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping(path = "/helloworldbean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello world Bean");
	}
	
	@GetMapping(path = "/helloworldbean/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("hello world Bean %s",name));
	}

}
