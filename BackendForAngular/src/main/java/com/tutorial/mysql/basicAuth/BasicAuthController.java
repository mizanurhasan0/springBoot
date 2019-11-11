package com.tutorial.mysql.basicAuth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthController {
	

	@GetMapping(path = "/basicauth")
	public BasicAuthModel helloWorldBean() {
		return new BasicAuthModel("Authorization User!");
	}

}
