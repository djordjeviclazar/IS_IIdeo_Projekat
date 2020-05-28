package com.ejb.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.ILicencaService;
import com.jpa.entities.Licenca;
import java.util.Date;
import java.util.Calendar;

@Stateless
public class LicencaService implements ILicencaService
{
	@PersistenceContext(name = "App_Site_v1.0")
	private EntityManager entityManager;
	
	@Override
	public int findUser(int idLicence)
	{
		Licenca licenca = this.entityManager.find(Licenca.class, idLicence);
		return licenca.getPremiumUser(); // vraća ID korisnika za tabelu Korisnik
	}
	
	@Override
	public void pretrplata(int idKorisnika)
	{
		Licenca licenca = entityManager.createQuery("SELECT l FROM Licenca l WHERE l.premiumUser = '" + idKorisnika + "'", Licenca.class).getSingleResult();
		
		entityManager.getTransaction().begin();
		
		Calendar noviDatum = Calendar.getInstance();
		noviDatum.add(Calendar.MONTH, 1);
		licenca.setDatum(noviDatum.getTime());
		
		entityManager.getTransaction().commit();
	}
	
	@Override
	public boolean validan(int idKorisnika)
	{
		Licenca licenca = entityManager.createQuery("SELECT l FROM Licenca l WHERE l.premiumUser = '" + idKorisnika + "'", Licenca.class).getSingleResult();
		return licenca.getDatum().compareTo(new Date()) > 0;
	}
}
