package com.blog.jwt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.jwt.model.Feedback;
import com.blog.jwt.repositories.FeedbackRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeedBackController {
	
	@Autowired
	final private FeedbackRepository feedbackRepository;
	public FeedBackController(FeedbackRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}

	@PostMapping("/sentFeedback")
	public Feedback create(@RequestBody Map<String, String> body) {
		String message = body.get("message");
		String email = body.get("email");
		System.out.println(email); 
		return feedbackRepository.save(new Feedback(message, email));
	}

}
