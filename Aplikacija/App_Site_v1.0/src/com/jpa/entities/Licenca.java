package com.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Table(name = "licenca")
public class Licenca 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Datum")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;
	
	@Column(name = "premium_userID")
	private int premiumUser;
	
	public int getId()
	{
		return this.id;
	}
	
	public Date getDatum()
	{
		return this.datum;
	}
	
	public void setDatum(Date datum)
	{
		this.datum = datum;
	}
	
	public int getPremiumUser()
	{
		return this.premiumUser;
	}
	
	public void setPremiumUser(int id)
	{
		this.premiumUser = id;
	}
}
