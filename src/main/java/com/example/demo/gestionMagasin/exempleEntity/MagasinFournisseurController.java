package com.example.demo.gestionMagasin.exempleEntity;

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

import com.example.demo.entities.Fournisseur;


@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/Fournisseur")
public class MagasinFournisseurController {
	@Autowired
	private MagasinFournisseurServices FournisseurServices  ;
	@GetMapping()
	public ResponseEntity < ? > getAllFournisseurs() {
	   return FournisseurServices.getAllFournisseurs();
	} 
	@GetMapping("/{id}")
	public ResponseEntity < ? > getFournisseur(@PathVariable("id") Long id) {
	   return FournisseurServices.getFournisseur(id);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity < ? > UpdateFournisseur(@RequestBody Fournisseur  ufournisseur,@PathVariable("id") Long id)
	{
		return FournisseurServices.UpdateFournisseur(ufournisseur, id);
		
	}
	@PostMapping()
	public ResponseEntity < ? > addFournisseur(@RequestBody Fournisseur fournisseur) {
		
	return FournisseurServices.addFournisseur(fournisseur);}
	@DeleteMapping("/{id}")
	public ResponseEntity < ? > deleteFournisseur(@PathVariable("id") Long id) {
		
		return FournisseurServices.deleteFournisseur(id);
	}


}
