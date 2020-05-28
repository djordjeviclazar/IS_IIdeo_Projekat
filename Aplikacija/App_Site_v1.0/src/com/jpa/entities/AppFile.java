package com.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Table(name = "appfile")
public class AppFile 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Lob
	@Column(name = "Sadrzaj", columnDefinition = "BLOB")
	private byte[] sadrzaj;
	
	public int getId()
	{
		return this.id;
	}
	
	public byte[] getSadrzaj()
	{
		return this.sadrzaj;
	}
	
	public void setSadrzaj(byte[] sadrzaj)
	{
		this.sadrzaj = sadrzaj;
	}
}
