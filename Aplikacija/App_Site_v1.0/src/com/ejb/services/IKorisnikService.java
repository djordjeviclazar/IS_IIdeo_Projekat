package com.ejb.services;

import com.jpa.entities.Korisnik;
import java.lang.String;

public interface IKorisnikService 
{
	public void addKorisnik(Korisnik korisnik);
	public Korisnik getInfo(int id);
	public Korisnik validate(String username, String password);
}
