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

import com.example.demo.entities.Proprite;



@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/Proprite")
public class MagasinPropriteController {
	@Autowired
	private MagasinPropriteServices PropriteServices ;
	@GetMapping()
	public List<Proprite> getAllProprites() {
		return PropriteServices.getAllProprites();
		} 
	@GetMapping("/{id}")
	public Proprite getProprite(@PathVariable("id") Long id) {
		return PropriteServices.getProprite(id);
		}
	@PutMapping(value="/{id}")
	public void UpdateProprite(@RequestBody Proprite uproprite,@PathVariable("id") Long id)
	{
		PropriteServices.UpdateProprite(uproprite, id);
	}
	@PostMapping()
	public Proprite addProprite(@RequestBody Proprite proprite) {
	return PropriteServices.addProprite(proprite);}
	@DeleteMapping(value="/{id}")
	public void deleteProprite(@PathVariable("id") Long id)
	{
		PropriteServices.deleteProprite(id);
		
	}



}
