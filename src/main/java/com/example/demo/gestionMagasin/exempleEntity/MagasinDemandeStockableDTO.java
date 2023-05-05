package com.example.demo.gestionMagasin.exempleEntity;

import java.util.Collection;
import java.util.HashSet;

import com.example.demo.entities.DemandeStockable.Etat;
import com.example.demo.entities.DemandeUnStockable;
import com.example.demo.entities.Employer;
import com.example.demo.entities.Service;


public class MagasinDemandeStockableDTO {
	
	
	private String Description;
	private Etat etat;
	private Collection<DemandeUnStockable> DemandeUnStockables=new HashSet<DemandeUnStockable>();
	private ServiceDto service;
    private String source;
	private EmployerDto employer;



	public MagasinDemandeStockableDTO () {
		
	}
	public MagasinDemandeStockableDTO(String description, Etat etat,Collection<DemandeUnStockable> DemandeUnStockables,ServiceDto service,EmployerDto employer,String source) {
		Description = description;
		this.etat = etat;
		this.source=source;
		this.DemandeUnStockables= DemandeUnStockables;
		this.service=service;
		this.employer=employer;
		
	}
	
	public ServiceDto getService()
	{
		return service;
	}
	public void setService(ServiceDto service)
	{
		this.service=service;
	}
	public EmployerDto getEmployer()
	{
		return employer;
	}
	public void setEmployer(EmployerDto employer)
	{
		this.employer=employer;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public Collection<DemandeUnStockable> getDemandeUnStockables() {
		return DemandeUnStockables;
	}
	public void setDemandeUnStockables(Collection<DemandeUnStockable> DemandeUnStockables )
	{
		this.DemandeUnStockables=DemandeUnStockables;
	}
	

}
