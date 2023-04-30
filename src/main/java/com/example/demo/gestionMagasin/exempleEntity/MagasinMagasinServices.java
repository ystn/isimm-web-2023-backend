package com.example.demo.gestionMagasin.exempleEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Doa.MagasinRepository;
import com.example.demo.entities.Magasin;


@Service
public class MagasinMagasinServices {
	@Autowired
	private MagasinRepository MagasinDao  ;
	public List<Magasin> getAllMagasins() {
	List<Magasin> Magasins =  MagasinDao.findAll(); 
	return Magasins; 
	} 
	public Magasin getMagasin(Long id) {
	Optional<Magasin> oMagasin =  MagasinDao.findById(id); 
	Magasin magasin = oMagasin.get(); 
	return magasin;
	}
	public void UpdateMagasin(Magasin umagasin,Long id)
	{
		 try {
		Optional<Magasin> oMagasin =  MagasinDao.findById(id); 
		Magasin magasin = oMagasin.get(); 
		MagasinDao.save(umagasin);
		 }
		 catch(NoSuchElementException e){
			 
		 }
	}
	public Magasin addMagasin(Magasin fournisseur) {
	return MagasinDao.save( fournisseur);}
	public void deleteMagasin(Long id) {
		MagasinDao.deleteById(id);
	}


}
