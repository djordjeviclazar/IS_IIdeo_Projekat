package com.ejb.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.IPremiumUserService;
import com.jpa.entities.PremiumUser;

@Stateless
public class PremiumUserService implements IPremiumUserService
{
	@PersistenceContext(name = "App_Site_v1.0")
	private EntityManager entityManager;

	@Override
	public void addPremiumUser(int id) 
	{
		PremiumUser user = entityManager.createQuery("SELECT l FROM PremiumUser l WHERE l.userId = '" + id + "'", PremiumUser.class).getSingleResult();
		
		if (user == null)
		{
			user = new PremiumUser();
			user.setUserId(id);
			
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public int getSpecificId(int id) 
	{
		PremiumUser user = entityManager.createQuery("SELECT l FROM PremiumUser l WHERE l.userId = '" + id + "'", PremiumUser.class).getSingleResult();
		
		if (user == null)
		{
			return -1;
		}
		
		return user.getId();
	}

	@Override
	public boolean isPremiumUser(int id) 
	{
		PremiumUser user = entityManager.createQuery("SELECT l FROM PremiumUser l WHERE l.userId = '" + id + "'", PremiumUser.class).getSingleResult();
		
		return user != null;
	}

	@Override
	public int getId(int specificId) 
	{
		PremiumUser user = entityManager.createQuery("SELECT l FROM PremiumUser l WHERE l.id = '" + specificId + "'", PremiumUser.class).getSingleResult();
		
		if (user == null)
		{
			return -1;
		}
		
		return user.getUserId();
	}
}
