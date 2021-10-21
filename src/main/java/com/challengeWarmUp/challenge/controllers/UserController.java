package com.challengeWarmUp.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challengeWarmUp.challenge.daoImp.User;

@RestController
public class UserController {
	
	@Autowired
	private User user;
	
	@PostMapping(value="auth/sign_up")
	public  ResponseEntity<?> register(@RequestBody com.challengeWarmUp.challenge.models.User user){
		
		if(user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getEmail() == null)
			return ResponseEntity.badRequest().body("Fields cannot be null or empty");
		
		if(this.user.findByEmail(user.getEmail()) != null) {
			return ResponseEntity.badRequest().body("User alredy exists");
		}
		
		this.user.create(user);
		return ResponseEntity.ok("User has been register");
	}
}
