package com.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
@Table(name = "premium_user")
public class PremiumUser 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "userID")
	private int userId;
	
	public int getId()
	{
		return this.id;
	}
	
	public int getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(int id)
	{
		this.userId = id;
	}
}
