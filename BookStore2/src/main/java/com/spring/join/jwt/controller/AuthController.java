package com.spring.join.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.spring.join.jwt.model.AdminUser;
import com.spring.join.jwt.model.UserInfo;
import com.spring.join.jwt.repositories.AdminUserRepository;
import com.spring.join.jwt.repositories.UserInfoRepository;
import com.spring.join.jwt.requests.JwtRequest;
import com.spring.join.jwt.responsetoken.JwtResponse;
import com.spring.join.jwt.responsetoken.JwtToken;
import com.spring.join.jwt.services.JwtUserDetailsService;

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

	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		
		final String token = jwtToken.generateToken(userDetails);
		final String email = authenticationRequest.getEmail();

		int id= getuserId(email);
		return ResponseEntity.ok(new JwtResponse(token, email,id));

	}
	
	@RequestMapping(value = "/admin/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenForAdmin(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		
		final String token = jwtToken.generateToken(userDetails);
		final String email = authenticationRequest.getEmail();

		int id= getAdminId(email);
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
	
	private int getAdminId(String email) {
		AdminUser user=adminUserRepository.findByEmail(email);
		return user.getId();
	}

}
