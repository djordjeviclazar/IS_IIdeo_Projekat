package com.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.IAplikacijaService;
import com.jpa.entities.Aplikacija;

@Stateless
public class AplikacijaService implements IAplikacijaService
{
	@PersistenceContext(name = "App_Site_v1.0")
	private EntityManager entityManager;
	
	@Override
	public void Upload(Aplikacija aplikacija) 
	{
		entityManager.getTransaction().begin();
		entityManager.persist(aplikacija);
		entityManager.getTransaction().commit();
	}

	@Override
	public Aplikacija Download(int id) 
	{
		Aplikacija aplikacija = entityManager.createQuery("SELECT a FROM Aplikacija a WHERE a.id = '" + id + "'", Aplikacija.class).getSingleResult();
		return aplikacija;
	}

	@Override
	public List<Aplikacija> getVersions(String ime) 
	{
		List<Aplikacija> aplikacija= entityManager.createQuery("SELECT a FROM Aplikacija a WHERE a.imeFajla = '" + ime + "' ORDER BY a.verzija DESC", Aplikacija.class).getResultList();
		return aplikacija;
	}

	@Override
	public List<Aplikacija> getAll() 
	{
		List<Aplikacija> aplikacija= entityManager.createQuery("SELECT a FROM Aplikacija a WHERE a.poslednjaVerzija = '" + 0 + "'", Aplikacija.class).getResultList();
		return aplikacija;
	}

}
