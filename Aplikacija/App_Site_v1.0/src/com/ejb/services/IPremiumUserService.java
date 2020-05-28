package com.ejb.services;

public interface IPremiumUserService 
{
	public void addPremiumUser(int id);
	public int getSpecificId(int id);
	public boolean isPremiumUser(int id);
}
