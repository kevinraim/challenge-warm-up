package com.challengeWarmUp.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengeWarmUp.challenge.daoImp.PostDaoImp;
import com.challengeWarmUp.challenge.models.Post;

@RestController
public class PostController {
	
	@Autowired
	private PostDaoImp postImp;
	
	@GetMapping(value="/posts")
	public List<?> getAllOrderByDate(){
		return postImp.getOrderByDate();
	}
}
