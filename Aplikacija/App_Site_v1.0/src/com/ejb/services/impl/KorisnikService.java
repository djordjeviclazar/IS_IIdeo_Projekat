package com.ejb.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.IKorisnikService;
import com.jpa.entities.Korisnik;

@Stateless
public class KorisnikService implements IKorisnikService
{
	@PersistenceContext(name = "App_Site_v1.0")
	private EntityManager entityManager;

	@Override
	public void addKorisnik(Korisnik korisnik) 
	{
		entityManager.getTransaction().begin();
		entityManager.persist(korisnik);
		entityManager.getTransaction().commit();
	}

	@Override
	public Korisnik getInfo(int id) 
	{
		Korisnik user = entityManager.createQuery("SELECT l FROM Korisnik l WHERE l.id = '" + id + "'", Korisnik.class).getSingleResult();
		return user;
	}

	@Override
	public Korisnik validate(String username, String password) 
	{
		Korisnik user = entityManager.createQuery("SELECT l FROM Korisnik l WHERE l.username = '" + username + "'", Korisnik.class).getSingleResult();
		
		if (user != null && user.getPassword().equals(password))
		{
			return user;
		}
		
		return null;
	}
	
}
