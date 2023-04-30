package com.example.demo.gestionMagasin.exempleEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.FactureStockable;



@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/FactureStockable")
public class MagasinFactureStockableController {
	@Autowired
	private MagasinFactureStockableServices FactureStockableServices  ;
	@GetMapping()
	public List<FactureStockable> getAllFactureStockable() {
	     return  FactureStockableServices.getAllFactureStockable();
	} 
	@GetMapping("/{id}")
	public FactureStockable getFactureStockable(@PathVariable("id") Long id) {
	    return  FactureStockableServices.getFactureStockable(id);
	}
	@PutMapping(value="/{id}")
	public void UpdateFacture(@RequestBody FactureStockable  ufactureStockable,@PathVariable("id") Long id)
	{
		 FactureStockableServices.UpdateFacture(ufactureStockable, id);
	}
	@PostMapping()
	public FactureStockable addFactureStockable(@RequestBody FactureStockable  factureStockable) {
	return  FactureStockableServices.addFactureStockable(factureStockable);}
	@DeleteMapping("/{id}")
	public void deleteFactureStockable(@PathVariable("id") Long id) {
		 FactureStockableServices.deleteFactureStockable(id);
	}



}
