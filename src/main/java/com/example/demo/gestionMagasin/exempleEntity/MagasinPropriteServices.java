package com.example.demo.gestionMagasin.exempleEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Doa.PropriteRepository;
import com.example.demo.entities.Proprite;

@Service
public class MagasinPropriteServices {
	@Autowired
	private PropriteRepository PropriteDao  ;
	public List<Proprite> getAllProprites() {
		List<Proprite> Proprites =  PropriteDao.findAll(); 
		return Proprites; 
	} 
	public Proprite getProprite(Long id) {
		Optional<Proprite> oProprite =  PropriteDao.findById(id); 
		Proprite proprite = oProprite.get(); 
		return proprite;
	}
	public void UpdateProprite( Proprite uproprite,Long id)
	{
		 try {
		Optional<Proprite> oProprite =  PropriteDao.findById(id); 
		Proprite proprite = oProprite.get(); 
		PropriteDao.save(uproprite);
		 }
		 catch(NoSuchElementException e){
			 
		 }
	}
	public Proprite addProprite(Proprite proprite) {
	return PropriteDao.save( proprite);}
	public void deleteProprite(Long id)
	{
		PropriteDao.deleteById(id);
		
	}

}
