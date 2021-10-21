package com.challengeWarmUp.challenge.dao;

import java.util.List;

import com.challengeWarmUp.challenge.dto.Post;


public interface PostDaoImp{
	
	List<Post> getOrderByDate();
	
	List<Post> getByTitle(String title);
	
	List<Post> getByCategory(String category);
	
	List<Post> getById(Long id);
	
}
