package com.ejb.services;

import java.util.List;
import java.lang.String;
import com.jpa.entities.Aplikacija;

public interface IAplikacijaService 
{
	public void Upload(Aplikacija aplikacija);
	public Aplikacija Download(int id);
	public List<Aplikacija> getVersions(String ime);
	public List<Aplikacija> getAll();
}
