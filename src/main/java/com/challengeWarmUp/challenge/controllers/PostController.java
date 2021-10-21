package com.challengeWarmUp.challenge.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challengeWarmUp.challenge.daoImp.Post;

@RestController
public class PostController {
	
	@Autowired
	private Post post;
	
	@GetMapping(value="/posts")
	public List<com.challengeWarmUp.challenge.dto.Post> getAllOrderByDate(){
		return post.getOrderByDate();
	}
	
	@GetMapping(value="/post", params="title")
	public List<com.challengeWarmUp.challenge.dto.Post> getByTitle(@RequestParam String title){
		return post.getByTitle(title);
	}
	
	@GetMapping(value="/post", params="category")
	public List<com.challengeWarmUp.challenge.dto.Post> getByCategory(@RequestParam String category){
		return post.getByCategory(category);
	}
	
	@GetMapping(value="/post", params={"title", "category"})
	public List<com.challengeWarmUp.challenge.dto.Post> getByTitleAndCategory(@RequestParam String title, @RequestParam String category){
		return post.getByTitleAndCategory(title, category);
	}
	
	@GetMapping(value="/posts/{id}")
	public List<com.challengeWarmUp.challenge.dto.Post> getById(@PathVariable Long id){
		return post.getById(id);
	}
}