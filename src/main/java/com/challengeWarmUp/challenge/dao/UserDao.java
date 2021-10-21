package com.challengeWarmUp.challenge.dao;

import com.challengeWarmUp.challenge.models.User;

public interface UserDao {
	
	User findByEmail(String email);
	
	User create(User user);

	User findById(Long id);
}
