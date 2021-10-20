package com.challengeWarmUp.challenge.dao;

import java.util.List;

import com.challengeWarmUp.challenge.projections.PostProjection;


public interface PostDao{
	
	List<PostProjection> getOrderByDate();
	
	List<PostProjection> getByTitle(String title);
	
	List<PostProjection> getByCategory(String category);
	
	List<PostProjection> getById(Long id);
	
}
