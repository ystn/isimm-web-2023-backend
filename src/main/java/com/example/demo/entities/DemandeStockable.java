package com.example.demo.entities;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class DemandeStockable implements Serializable {
	@Id @GeneratedValue
	private Long idDemandeStockable;
	@Column(length=100)
	private String Description;
	public enum Etat {approved,rejected,pending,fullfield};
	private Etat etat;
	@OneToMany(mappedBy="demandeStockable")
	private Collection<DemandeUnStockable> DemandeUnStockables=new HashSet<DemandeUnStockable>();
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="service")
	private Service service =new Service();
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_employer")
	private Employer employer=new Employer();



	public DemandeStockable() {
		
	}
	public DemandeStockable(String description, Etat etat) {
		Description = description;
		this.etat = etat;
	}
	public Service getService()
	{
		return service;
	}
	public void setService(Service service)
	{
		this.service=service;
	}
	public Employer getEmployer()
	{
		return employer;
	}
	public void setEmployer(Employer employer)
	{
		this.employer=employer;
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
	public Long getIdDemandeStockable() {
		return idDemandeStockable;
	}
	public void setIdDemandeStockable(Long idDemandeStockable) {
		this.idDemandeStockable=idDemandeStockable;
	}
	public Collection<DemandeUnStockable> getDemandeUnStockables() {
		return DemandeUnStockables;
	}
	public void setDemandeUnStockables(Collection<DemandeUnStockable> DemandeUnStockables )
	{
		this.DemandeUnStockables=DemandeUnStockables;
	}
	
	
}
