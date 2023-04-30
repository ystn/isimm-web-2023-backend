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

import com.example.demo.entities.Stockable;


@CrossOrigin
@RestController
@RequestMapping("api/isimm/gestionMagasin/magasin/Stockable")
public class MagasinStockableController {

    private final MagasinStockableServices StockableServices;
    
    public MagasinStockableController(MagasinStockableServices entityService){
        this.StockableServices=entityService;
    }
	@GetMapping()
	public List<Stockable> getAllServices() {
		return StockableServices.getAllServices();
	} 
	@GetMapping("/{id}")
	public Stockable getStockable(@PathVariable("id") Long id) {
		return StockableServices.getStockable(id);
	}
	@PutMapping(value="/{id}")
	public void UpdateStockable(@RequestBody Stockable ustockable,@PathVariable("id") Long id)
	{
		StockableServices.UpdateStockable(ustockable, id);
	}
	@PostMapping()
	public Stockable addStockable(@RequestBody Stockable stockable) {
	return StockableServices.addStockable(stockable);}
	@DeleteMapping("/{id}")
	public void deleteStockable(@PathVariable("id") Long id) {
		StockableServices.deleteStockable(id);
	}


}
