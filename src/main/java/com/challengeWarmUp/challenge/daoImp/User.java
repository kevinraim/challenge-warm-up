package com.challengeWarmUp.challenge.daoImp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challengeWarmUp.challenge.dao.UserDao;

@Repository
@Transactional
@Service
public class User implements UserDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public com.challengeWarmUp.challenge.models.User create(com.challengeWarmUp.challenge.models.User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return em.merge(user);
	}	
	
	@Override
	public com.challengeWarmUp.challenge.models.User findByEmail(String email) {
		TypedQuery<com.challengeWarmUp.challenge.models.User> q = em.createQuery("From User WHERE email="+ "'" + email + "'", com.challengeWarmUp.challenge.models.User.class);
		
		try {
			return q.getSingleResult();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public com.challengeWarmUp.challenge.models.User findById(Long id) {
		return em.find(com.challengeWarmUp.challenge.models.User.class, id);
	}

}
