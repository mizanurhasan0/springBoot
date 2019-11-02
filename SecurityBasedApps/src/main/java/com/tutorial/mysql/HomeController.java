package com.tutorial.mysql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(path = "/")
	public String home() {
		return "this is home";
	}
	
	
}
