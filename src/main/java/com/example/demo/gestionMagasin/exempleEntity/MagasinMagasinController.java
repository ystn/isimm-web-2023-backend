package com.example.demo.gestionMagasin.exempleEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Magasin;



@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/Magasin")
public class MagasinMagasinController {
	@Autowired
	private MagasinMagasinServices MagasinServices ;
	@GetMapping()
	public List<Magasin> getAllMagasins() {
		return MagasinServices.getAllMagasins();
	} 
	@GetMapping("/{id}")
	public Magasin getMagasin(@PathVariable("id") Long id) {
	   return MagasinServices.getMagasin(id);
	}
	@PutMapping(value="/{id}")
	public void UpdateMagasin(@RequestBody Magasin umagasin,@PathVariable("id") Long id)
	{
		MagasinServices.UpdateMagasin(umagasin, id);
	}
	@PostMapping()
	public Magasin addMagasin(@RequestBody Magasin magasin) {
	return MagasinServices.addMagasin(magasin);}
	@DeleteMapping("/{id}")
	public void deleteMagasin(@PathVariable("id") Long id) {
		MagasinServices.deleteMagasin(id);      
	}



}
