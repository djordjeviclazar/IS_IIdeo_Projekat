package com.gui.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.ejb.services.impl.AplikacijaService;
import com.ejb.services.impl.AppFileService;
import com.ejb.services.impl.KorisnikService;
import com.ejb.services.impl.LicencaService;
import com.ejb.services.impl.PremiumUserService;
import com.gui.DTOs.AplikacijaDTO;
import com.gui.utils.SessionUtils;
import com.jpa.entities.Aplikacija;
import com.jpa.entities.AppFile;
import com.jpa.entities.Korisnik;

@ManagedBean
@SessionScoped
public class AplikacijaController implements Serializable
{
	private String selectedApp, searchCriteria;
	private Part fajl;
	
	@EJB
	private AplikacijaService appService;
	
	@EJB
	private AppFileService fileService;
	
	@EJB
	private LicencaService licencaService;
	
	@EJB
	private PremiumUserService premiumUserService;
	
	@EJB
	private KorisnikService korisnikService; 
	
	public String getSelectedApp()
	{
		return this.selectedApp;
	}
	
	public void setSelectedApp(String selected)
	{
		this.selectedApp = selected;
	}
	
	public String getSearchCriteria()
	{
		return this.searchCriteria;
	}
	
	public void setSearchCriteria(String criteria)
	{
		this.searchCriteria = criteria;
	}
	
	public Part getFajl()
	{
		return this.fajl;
	}
	
	public void setFajl(Part fajl)
	{
		this.fajl = fajl;
	}
	
	public List<AplikacijaDTO> prikazHomeApp()
	{
		List<AplikacijaDTO> retList = new ArrayList<AplikacijaDTO>();
		
		List<Aplikacija> lista = appService.getAll();
		for (Aplikacija a : lista)
		{
			AplikacijaDTO novi = new AplikacijaDTO();
			novi.id = a.getId();
			novi.imeFajla = a.getImeFajla();
			novi.ekstenzija = a.getEkstenzija();
			novi.verzija = a.getVerzija();
			
			Korisnik autor = korisnikService.getInfo(premiumUserService.getId(licencaService.findUser(appService.Download(a.getId()).getLicenca())));
			novi.autor = autor.getPunoIme();
			
			retList.add(novi);
		}
		
		return retList;
	}
	
	public List<AplikacijaDTO> prikazFilter()
	{
		List<AplikacijaDTO> retList = new ArrayList<AplikacijaDTO>();
		
		List<Aplikacija> lista = appService.getAll();
		for (Aplikacija a : lista)
		{
			if (!a.getImeFajla().contains(searchCriteria))
			{
				continue;
			}
			AplikacijaDTO novi = new AplikacijaDTO();
			novi.id = a.getId();
			novi.imeFajla = a.getImeFajla();
			novi.ekstenzija = a.getEkstenzija();
			novi.verzija = a.getVerzija();
			
			Korisnik autor = korisnikService.getInfo(premiumUserService.getId(licencaService.findUser(appService.Download(a.getId()).getLicenca())));
			novi.autor = autor.getPunoIme();
			
			retList.add(novi);
		}
		
		return retList;
	}
	
	public List<AplikacijaDTO> prikazSingle()
	{
		List<AplikacijaDTO> retList = new ArrayList<AplikacijaDTO>();
		
		List<Aplikacija> lista = appService.getVersions(selectedApp);
		for (Aplikacija a : lista)
		{
			AplikacijaDTO novi = new AplikacijaDTO();
			novi.id = a.getId();
			novi.imeFajla = a.getImeFajla();
			novi.ekstenzija = a.getEkstenzija();
			novi.verzija = a.getVerzija();
			
			Korisnik autor = korisnikService.getInfo(premiumUserService.getId(licencaService.findUser(appService.Download(a.getId()).getLicenca())));
			novi.autor = autor.getPunoIme();
			
			retList.add(novi);
		}
		
		return retList;
	}
	
	public void download(int id)
	{
		 String imeFajla = "C:\\ProgramFiles\\NewApp\\";
		 try
		 {
			 Aplikacija app = appService.Download(id);
			 AppFile fajl = fileService.getFile(app.getSadrzaj());
			 imeFajla = imeFajla + app.getImeFajla() + "." + app.getEkstenzija();
			 
		     byte[] bafer = fajl.getSadrzaj();
		     
		     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		    
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Disposition", "attachment;filename=" + imeFajla);
		    response.getOutputStream().write(bafer);
		    response.getOutputStream().flush();
		    response.getOutputStream().close();
		    FacesContext.getCurrentInstance().responseComplete();
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
	}
	
	public void upload()
	{
		if (this.fajl != null)
		{
			try
			{
				InputStream tok = this.fajl.getInputStream();
				
				long size = this.fajl.getSize();
				if (size > Integer.MAX_VALUE)
				{
					throw new Exception("Preveliki fajl");
				}
				
				byte[] bajtoviFajla = new byte[(int) size];
				tok.read(bajtoviFajla);
				
				AppFile novi = new AppFile();
				Aplikacija nova =  new Aplikacija();
				Date date = new Date();
				
				HttpSession session = SessionUtils.getSession();
				Korisnik korisnik = korisnikService.getInfo(Integer.parseInt(session.getId()));
				nova.setBrojOcena(0);
				nova.setDatumKreiranja(date);
				nova.setPoslednjaVerzija(Byte.parseByte("1"));
				nova.setBrojOcena(0);
				nova.setLicenca(licencaService.getLicenca(premiumUserService.getSpecificId(korisnik.getId())));
				
				fileService.Upload(novi);
				
				nova.setImeFajla(fajl.getName());
				nova.setEkstenzija(".exe");
				nova.setSadrzaj(fileService.freePlace());
				appService.Upload(nova);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
