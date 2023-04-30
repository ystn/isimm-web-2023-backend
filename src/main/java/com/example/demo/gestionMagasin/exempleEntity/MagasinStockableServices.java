package com.example.demo.gestionMagasin.exempleEntity;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Doa.StockableRepository;
import com.example.demo.entities.Stockable;



@Service
@AllArgsConstructor
public class MagasinStockableServices {
	@Autowired
	private StockableRepository StockableDao  ;
	public List<Stockable> getAllServices() {
		List<Stockable>Stockables =  StockableDao.findAll(); 
		return Stockables; 
	} 
	public Stockable getStockable(Long id) {
		Optional<Stockable> ostockable =  StockableDao.findById(id); 
		Stockable stockable = ostockable.get(); 
		return stockable;
	}
	public void UpdateStockable(Stockable ustockable,Long id)
	{
		 try {
		Optional<Stockable> oStockable =  StockableDao.findById(id); 
		Stockable stockable = oStockable.get(); 
		StockableDao.save(ustockable);
		 }
		 catch(NoSuchElementException e){
			 
		 }
	}
	public Stockable addStockable(Stockable stockable) {
	return StockableDao.save( stockable);}
	public void deleteStockable(Long id) {
		StockableDao.deleteById(id);
	}

}
