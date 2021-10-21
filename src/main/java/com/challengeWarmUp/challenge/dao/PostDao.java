package com.challengeWarmUp.challenge.dao;

import java.util.List;

import com.challengeWarmUp.challenge.dto.Post;


public interface PostDao{
	
	List<Post> getOrderByDate();
	
	List<Post> getByTitle(String title);
	
	List<Post> getByCategory(String category);
	
	List<Post> getByTitleAndCategory(String title, String category);
	
	Post getById(Long id);
	
	com.challengeWarmUp.challenge.models.Post create(com.challengeWarmUp.challenge.models.Post Post);
	
	boolean remove(Long id);
}
