package com.example.demo.gestionMagasin.exempleEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Doa.FactureStockableRepository;
import com.example.demo.entities.FactureStockable;



@Service
public class MagasinFactureStockableServices {
	@Autowired
	private FactureStockableRepository FactureStockableDao  ;
	public List<FactureStockable> getAllFactureStockable() {
	List<FactureStockable> FactureStockables =  FactureStockableDao.findAll(); 
	return  FactureStockables; 
	} 
	public FactureStockable getFactureStockable(Long id) {
	Optional<FactureStockable> oFactureStockable =  FactureStockableDao.findById(id); 
	FactureStockable  factureStockable = oFactureStockable.get(); 
	return factureStockable;
	}
	public void UpdateFacture(FactureStockable  ufactureStockable,Long id)
	{
		 try {
		Optional<FactureStockable> oFactureStockable =  FactureStockableDao.findById(id); 
		FactureStockable factureStockable = oFactureStockable.get(); 
		FactureStockableDao.save(ufactureStockable);
		 }
		 catch(NoSuchElementException e){
			 
		 }
	}
	public FactureStockable addFactureStockable(FactureStockable  factureStockable) {
	return FactureStockableDao.save( factureStockable);}
	public void deleteFactureStockable(Long id) {
		FactureStockableDao.deleteById(id);
	}

}
