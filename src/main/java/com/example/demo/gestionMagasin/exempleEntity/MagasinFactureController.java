package com.example.demo.gestionMagasin.exempleEntity;

import java.util.List;
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

import com.example.demo.entities.Facture;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/Facture")
public class MagasinFactureController {
	@Autowired
	private MagasinFactureServices FactureServices  ;
	@GetMapping()
	public ResponseEntity < ? > getAllFactures() {
	 return FactureServices.getAllFactures();
	} 
	@GetMapping("/{id}")
	public ResponseEntity < ? > getFacture(@PathVariable("id") Long id) {
	  return FactureServices.getFacture(id);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity < ? > UpdateFacture(@RequestBody Facture  ufacture,@PathVariable("id") Long id)
	{
		return FactureServices.UpdateFacture(ufacture, id);
	}
	@PostMapping()
	public ResponseEntity < ? > addFacture(@RequestBody Facture  facture) {
		
	return FactureServices.addFacture(facture);}
	@DeleteMapping("/{id}")
	public ResponseEntity < ? > deleteFacture(@PathVariable("id") Long id) {
		return FactureServices.deleteFacture(id);
	}



	

}
