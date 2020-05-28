package com.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import java.lang.String;


@Entity
@Table(name = "aplikacija")
public class Korisnik 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "PunoIme")
	private String punoIme;
	
	@Column(name = "Email")
	private String email;
	
	public int getId()
	{
		return this.id;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String username)
	{
		if (username != null)
		{
			this.username = username;
		}
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		if (password != null)
		{
			this.password = password;
		}
	}
	
	public String getPunoIme()
	{
		return this.punoIme;
	}
	
	public void setPunoIme(String novoIme)
	{
		if (novoIme != null)
		{
			this.punoIme = novoIme;
		}
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String nova)
	{
		if (nova != null)
		{
			this.email = nova;
		}
	}
}