package com.challengeWarmUp.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengeWarmUp.challenge.daoImp.Post;

@RestController
public class PostController {
	
	@Autowired
	private Post postImp;
	
	@GetMapping(value="/posts")
	public List<com.challengeWarmUp.challenge.dto.Post> getAllOrderByDate(){
		return postImp.getOrderByDate();
	}
}
