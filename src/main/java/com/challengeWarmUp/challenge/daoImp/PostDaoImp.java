package com.challengeWarmUp.challenge.daoImp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.challengeWarmUp.challenge.dao.PostDao;
import com.challengeWarmUp.challenge.projections.PostProjection;

@Repository
@Transactional
public class PostDaoImp implements PostDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<PostProjection> getOrderByDate() {
		return em.createQuery("From Post ORDER BY date DESC", PostProjection.class).getResultList();
	}

	@Override
	public List<PostProjection> getByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostProjection> getByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostProjection> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
