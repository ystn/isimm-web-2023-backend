package com.example.demo.entities;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Stockable implements Serializable {
	@Id @GeneratedValue
	private Long idStockable;
    
	@Column
	private String refStockable;
	@Column(length=100)
	private String name;
	@ManyToOne
	@JoinColumn(name="magasin")
	private Magasin magasin;
    private Long quantite=new Long(0);
    private Date dateCreation;
	@OneToMany(mappedBy="stockable")
	private Collection<FactureStockable> FactureStockables=new HashSet<FactureStockable>();

	@OneToMany(mappedBy="stockable")
	private Collection<DemandeUnStockable> Demandes=new HashSet<DemandeUnStockable>();


	public Stockable() {
		
	}
	public Stockable(String name,Magasin magasin,Date dateCreation ,String refStockable) {
		this.name = name;
		this.magasin=magasin;
		this.dateCreation=dateCreation;
		this.refStockable=refStockable;
	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	}

	public String getRefStockable() {
		return refStockable;
	}

	public void setRefStockable(String refStockable) {
		this.refStockable = refStockable;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Magasin getMagasin()
	{
		return magasin;
	}
	public void setMagasin(Magasin magasin)
	{
		this.magasin=magasin;
	}
	public Long getIdStockable() {
		return idStockable;
	}
	public void setIdStockable(Long idStockable) {
		this.idStockable=idStockable;
	}

	@JsonIgnore
	public Collection<DemandeUnStockable> getDemandes()
	{
		return Demandes;
	}
	public void setDemandes(Collection<DemandeUnStockable> Demandes )
	{
		this.Demandes=Demandes;
	}

	@JsonIgnore
	public Collection<FactureStockable> getFactureStockables()
	{
		return FactureStockables;
	}

	public void setFactureStockables(Collection<FactureStockable> FactureStockables )
	{
		this.FactureStockables=FactureStockables;
	}


	public void addDemande(DemandeUnStockable demandeUnStockable){
		this.Demandes.add(demandeUnStockable);
	}

	public void addFactureStockable(FactureStockable factureStockable )
	{
		this.FactureStockables.add(factureStockable);
	}
	
}
