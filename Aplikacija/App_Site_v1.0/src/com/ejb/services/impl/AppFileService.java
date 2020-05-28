package com.ejb.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.IAppFIleService;
import com.jpa.entities.AppFile;

@Stateless
public class AppFileService implements IAppFIleService
{
	@PersistenceContext(name = "App_Site_v1.0")
	private EntityManager entityManager;
	
	@Override
	public AppFile getFile(int id) 
	{
		AppFile file = entityManager.createQuery("SELECT f FROM AppFile f WHERE f.id = '" + id + "'", AppFile.class).getSingleResult();
		return file;
	}

	@Override
	public void Upload(AppFile newFile) 
	{
		entityManager.getTransaction().begin();
		entityManager.persist(newFile);
		entityManager.getTransaction().commit();
	}

	@Override
	public int freePlace() 
	{
		AppFile file = entityManager.createQuery("SELECT f FROM AppFile f ORDER BY f.id DESC", AppFile.class).getSingleResult();
		return file.getId() + 1;
	}

}
