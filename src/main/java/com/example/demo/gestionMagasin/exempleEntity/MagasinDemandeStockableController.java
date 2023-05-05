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

import com.example.demo.entities.DemandeStockable;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/DemandeStockable")
public class MagasinDemandeStockableController {
	@Autowired
	private MagasinDemandeStockableServices DemandeStockableServices  ;
	@GetMapping()
	public ResponseEntity < ? > getAllDemandeStockables() {
	return DemandeStockableServices.getAllDemandeStockables();
	} 
	@GetMapping("/{id}")
	public ResponseEntity < ? > getDemandeStockables(@PathVariable("id") Long id) {
	return DemandeStockableServices.getDemandeStockables(id);
	}
	@PostMapping()
	public ResponseEntity < ? > addDemandeStockable(@RequestBody MagasinDemandeStockableDTO  demandeStockablesdto) {
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
		DemandeStockable d=MagasinDemandeStockableMapper.mapToDemandeStockable(demandeStockablesdto);
		return  DemandeStockableServices.addDemandeStockable(d);
		
		}
	@PutMapping("/{id}")
	public ResponseEntity < ? > UpdateDemandeStockable(@RequestBody MagasinDemandeStockableDTO  demandeStockablesdto,@PathVariable("id") Long id)
	{
		DemandeStockable d=MagasinDemandeStockableMapper.mapToDemandeStockable(demandeStockablesdto);
            	return DemandeStockableServices.UpdateDemandeStockable(d, id);
           
	}
	@DeleteMapping("/{id}")
	public ResponseEntity < ? >  deleteDemandeStockable(@PathVariable("id") Long id) {
		return DemandeStockableServices.deleteDemandeStockable(id);
	}


}
