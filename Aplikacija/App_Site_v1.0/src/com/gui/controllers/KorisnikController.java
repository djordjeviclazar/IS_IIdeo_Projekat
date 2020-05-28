package com.gui.controllers;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.faces.application.FacesMessage;

import com.ejb.services.impl.KorisnikService;
import com.ejb.services.impl.PremiumUserService;
import com.gui.DTOs.KorisnikDTO;
import com.gui.utils.SessionUtils;
import com.jpa.entities.Aplikacija;
import com.jpa.entities.Korisnik;
import com.sun.net.httpserver.HttpServer;

@ManagedBean
@SessionScoped
public class KorisnikController 
{
	private static final long serialVersionUID = 1094801825228386363L;
	
	private String password, username, email, punoIme, msg;
	private String search;
	
	@EJB
	private KorisnikService korisnikService;
	
	@EJB
	private PremiumUserService premiumUserService;
	
	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String pwd) 
	{
		this.password = pwd;
	}

	public String getMsg() 
	{
		return msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getUser() 
	{
		return username;
	}

	public void setUser(String username) 
	{
		this.username = username;
	}
	
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPunoIme() 
	{
		return msg;
	}

	public void setPunoIme(String ime) 
	{
		this.punoIme = ime;
	}
	
	public String getSearch() 
	{
		return search;
	}

	public void setSearch(String search) 
	{
		this.search = search;
	}
	
	public String validate() 
	{
			Korisnik korisnik = korisnikService.validate(username, password);
			if (korisnik != null) 
			{
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("idUser", korisnik.getId());
				return "Profile.xhtml";
			} 
			else 
			{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));
				return "Login";
			}
	}
	
	public String validatePremium() 
	{
			Korisnik korisnik = korisnikService.validate(username, password);
			if (korisnik != null && premiumUserService.isPremiumUser(korisnik.getId())) 
			{
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("idUser", korisnik.getId());
				return "ProfilePremium";
			} 
			else 
			{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));
				return "LoginPremium";
			}
	}
		
	public String register()
	{
		Korisnik korisnik = new Korisnik();
		korisnik.setEmail(email);
		korisnik.setUsername(username);
		korisnik.setPassword(password);
		korisnik.setPunoIme(punoIme);
		
		korisnikService.addKorisnik(korisnik);
		
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("idUser", korisnik.getId());
		return "Profile";
	}
	
	public String registerPremium()
	{
		Korisnik korisnik = new Korisnik();
		korisnik.setEmail(email);
		korisnik.setUsername(username);
		korisnik.setPassword(password);
		korisnik.setPunoIme(punoIme);
		
		korisnikService.addKorisnik(korisnik);
		
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("idUser", korisnik.getId());
		return "ProfilePremium";
	}
	
	public String logout() 
	{
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login";
	}
}
