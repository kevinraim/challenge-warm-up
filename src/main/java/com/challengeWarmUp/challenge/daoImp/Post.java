package com.challengeWarmUp.challenge.daoImp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class Post implements com.challengeWarmUp.challenge.dao.PostDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<com.challengeWarmUp.challenge.dto.Post> getOrderByDate() {
		TypedQuery<com.challengeWarmUp.challenge.dto.Post> q = em.createQuery("Select new com.challengeWarmUp.challenge.dto.Post(p.id, p.title, p.image, p.category, p.date)" + 
								" From Post p ORDER BY date DESC", com.challengeWarmUp.challenge.dto.Post.class);
		
		return q.getResultList();
	}

	@Override
	public List<com.challengeWarmUp.challenge.dto.Post> getByTitle(String title) {
		TypedQuery<com.challengeWarmUp.challenge.dto.Post> q = em.createQuery("Select new com.challengeWarmUp.challenge.dto.Post(p.id, p.title, p.image, p.category, p.date)" + 
				" From Post p" +
				" Where title="+ "'" + title + "'" +
				" ORDER BY date DESC", com.challengeWarmUp.challenge.dto.Post.class);
		return q.getResultList();
	}

	@Override
	public List<com.challengeWarmUp.challenge.dto.Post> getByCategory(String category) {
		TypedQuery<com.challengeWarmUp.challenge.dto.Post> q = em.createQuery("Select new com.challengeWarmUp.challenge.dto.Post(p.id, p.title, p.image, p.category, p.date)" + 
				" From Post p" +
				" Where category=" + "'" + category + "'" +
				" ORDER BY date DESC", com.challengeWarmUp.challenge.dto.Post.class);
		return q.getResultList();
	}
	
	@Override
	public List<com.challengeWarmUp.challenge.dto.Post> getByTitleAndCategory(String title, String category) {
		TypedQuery<com.challengeWarmUp.challenge.dto.Post> q = em.createQuery("Select new com.challengeWarmUp.challenge.dto.Post(p.id, p.title, p.image, p.category, p.date)" + 
				" From Post p" +
				" Where category=" + "'" + category + "'" + " and title="+ "'" + title + "'" +
				" ORDER BY date DESC", com.challengeWarmUp.challenge.dto.Post.class);
		return q.getResultList();
	}
	
	@Override
	public com.challengeWarmUp.challenge.dto.Post getById(Long id) {
		TypedQuery<com.challengeWarmUp.challenge.dto.Post> q = em.createQuery("Select new com.challengeWarmUp.challenge.dto.Post(p.id, p.title, p.image, p.category, p.date)" + 
				" From Post p" +
				" Where id=" + id, com.challengeWarmUp.challenge.dto.Post.class);
		
		try {
			return q.getSingleResult();
		}
		catch(NoResultException e) {
			e.printStackTrace(); //It's this ok?
			return null;
		}
	}

	@Override
	public com.challengeWarmUp.challenge.models.Post create(com.challengeWarmUp.challenge.models.Post Post) {
		return em.merge(Post);
	}

	@Override
	public boolean remove(Long id) {
		com.challengeWarmUp.challenge.models.Post postToRemove = em.find(com.challengeWarmUp.challenge.models.Post.class, id);
		
		if(postToRemove == null)
			return false;
		
		em.remove(postToRemove);
		return true;
	}
	
}
