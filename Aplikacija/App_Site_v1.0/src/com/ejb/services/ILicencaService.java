package com.ejb.services;

public interface ILicencaService 
{
	public int findUser(int idLicence);
	public void pretrplata(int idKorisnika);
	public boolean validan(int idKorisnika);
	public int getLicenca(int idKorisnika);
}
