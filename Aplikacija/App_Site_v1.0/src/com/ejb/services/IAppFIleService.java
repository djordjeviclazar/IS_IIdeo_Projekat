package com.ejb.services;

import com.jpa.entities.AppFile;

public interface IAppFIleService 
{
	public AppFile getFile(int id);
	public void Upload(AppFile newFile);
}
