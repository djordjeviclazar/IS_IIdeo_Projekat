package com.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import java.util.Date;
import java.lang.String;

@Entity
@Table(name = "aplikacija")
public class Aplikacija 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "DatumKreiranja")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumKreiranja;
	
	@Column(name = "ImeFajla")
	private String imeFajla;
	
	@Column(name = "Ekstenzija")
	private String ekstenzija;
	
	@Column(name = "BrojOcena")
	private int brojOcena;
	
	@Column(name = "Ocena")
	private double ocena;
	
	@Column(name = "Sadrzaj")
	private int sadrzaj;
	
	@Column(name = "Licenca")
	private int licenca;
	
	@Column(name = "Verzija")
	private double verzija;
	
	@Column(name = "PoslednjaVerzija")
	private byte poslednjaVerzija;
	
	public int getId()
	{
		return this.id;
	}
	
	public String getImeFajla()
	{
		return this.imeFajla;
	}
	
	public void setImeFajla(String novoIme)
	{
		if (novoIme != null)
		{
			this.imeFajla = novoIme;
		}
	}
	
	public String getEkstenzija()
	{
		return this.ekstenzija;
	}
	
	public void setEkstenzija(String nova)
	{
		if (nova != null)
		{
			this.ekstenzija = nova;
		}
	}
	
	public int getBrojOcena()
	{
		return this.brojOcena;
	}
	
	public void setBrojOcena(int n)
	{
		if (n >= 0)
		{
			this.brojOcena = n;
		}
	}
	
	public double getOcena()
	{
		return this.ocena;
	}
	
	public void setOcena(double ocena)
	{
		this.ocena = ocena;
	}
	
	public Date getDatumKreiranja()
	{
		return this.datumKreiranja;
	}
	
	public void setDatumKreiranja(Date datum)
	{
		this.datumKreiranja = datum;
	}
	
	public int getLicenca()
	{
		return this.licenca;
	}
	
	public void setLicenca(int noviId)
	{
		this.licenca = noviId;
	}
	
	public double getVerzija()
	{
		return this.verzija;
	}
	
	public void setVerija(double verzija)
	{
		this.verzija = verzija;
	}
	
	public byte getPoslednjaVerzija()
	{
		return this.poslednjaVerzija;
	}
	
	public void setPoslednjaVerzija(byte p)
	{
		this.poslednjaVerzija = p;
	}
	
	public int getSadrzaj()
	{
		return this.sadrzaj;
	}
	
	public void setSadrzaj(int fileId)
	{
		this.sadrzaj = fileId;
	}
}
