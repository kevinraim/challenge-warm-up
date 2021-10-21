package com.challengeWarmUp.challenge.dao;

import java.util.List;

import com.challengeWarmUp.challenge.dto.Post;


public interface PostDao{
	
	List<Post> getOrderByDate();
	
	List<Post> getByTitle(String title);
	
	List<Post> getByCategory(String category);
	
	List<Post> getByTitleAndCategory(String title, String category);
	
	List<Post> getById(Long id);
	
}
