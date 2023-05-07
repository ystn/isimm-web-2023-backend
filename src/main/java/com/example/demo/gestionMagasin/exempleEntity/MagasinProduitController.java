package com.example.demo.gestionMagasin.exempleEntity;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Produit;
import com.example.demo.entities.Proprite;




@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/Produit")
public class MagasinProduitController {
	@Autowired
	private MagasinProduitServices ProduitServices  ;
	@GetMapping()
	public ResponseEntity < ? > getAllProduits() {
		return ProduitServices.getAllProduits();
	} 
	@GetMapping("/{id}")
	public ResponseEntity < ? > getProduit(@PathVariable("id") Long id) {
		return ProduitServices.getProduit(id);
	}
	@GetMapping("/PropProduits/{id}")
	public Collection<Proprite> getPropsofProduit(@PathVariable("id") Long id) {
		return ProduitServices.getPropsofProduit(id);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity < ? > UpdateProduit(@RequestBody Produit uproduit,@PathVariable("id") Long id)
	{
		return ProduitServices.UpdateProduit(uproduit, id);
	}
	@PutMapping(value="/UpdateQuantite/{id}")
	public ResponseEntity < ? > UpdateQuantiteProduit(@RequestBody Produit uproduit,@PathVariable("id") Long id)
	{
		return ProduitServices.UpdateQuantiteProduit(uproduit, id);
	}
	@PutMapping(value="/AddProprite/{id}")
	public void addProprites(@RequestBody Proprite proprite ,@PathVariable("id") Long id  )
	{
		ProduitServices.addProprites(proprite, id);
	}
	@PostMapping()
	public ResponseEntity < ? > addProduit(@RequestBody Produit produit) {
		return ProduitServices.addProduit(produit);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity < ? > deleteProduit(@PathVariable("id") Long id)
	{
		return ProduitServices.deleteProduit(id);
	}
}
