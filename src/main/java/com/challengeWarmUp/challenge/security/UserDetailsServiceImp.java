package com.challengeWarmUp.challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.challengeWarmUp.challenge.models.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Lazy
	@Autowired
	private com.challengeWarmUp.challenge.daoImp.User user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.user.findByEmail(username);
		UserBuilder builder = null;
		
		if(user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else {
			throw new UsernameNotFoundException("Not Found User");
		}
		return builder.build();
	}

}
