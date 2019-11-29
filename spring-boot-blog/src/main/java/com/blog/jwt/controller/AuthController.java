package com.blog.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.blog.jwt.model.UserInfo;
import com.blog.jwt.repositories.UserInfoRepository;
import com.blog.jwt.requests.JwtRequest;
import com.blog.jwt.responsetoken.JwtResponse;
import com.blog.jwt.responsetoken.JwtToken;
import com.blog.jwt.services.JwtUserDetailsService;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtToken jwtToken;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		
		final String token = jwtToken.generateToken(userDetails);
		final String email = authenticationRequest.getEmail();

		int id= getuserId(email);
		return ResponseEntity.ok(new JwtResponse(token, email,id));

	}

	private void authenticate(String email, String password) throws Exception {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED", e);

		} catch (BadCredentialsException e) {

			throw new Exception("INVALID_CREDENTIALS", e);

		}

	}
	
	private int getuserId(String email) {
		UserInfo user=userInfoRepository.findByEmail(email);
		
		return user.getId();
	}

}
