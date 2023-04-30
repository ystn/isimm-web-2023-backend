package com.example.demo.gestionMagasin.exempleEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Doa.FournisseurRepository;
import com.example.demo.entities.Fournisseur;



@Service
public class MagasinFournisseurServices {
	@Autowired
	private FournisseurRepository FournisseurDao  ;
	public ResponseEntity < ? > getAllFournisseurs() {
	List<Fournisseur> Fournisseurs =  FournisseurDao.findAll(); 
	 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
	 if(!Fournisseurs.isEmpty())
	 {
		 respJsonOutput.put("status", 1);

		    respJsonOutput.put("message", "Records is Fectched  Successfully!");
		    respJsonOutput.put("Body",Fournisseurs);
		    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
	 }
	 else {
		 respJsonOutput.put("status", 0);

	      respJsonOutput.put("message", "No Founisseurs exists");
	      respJsonOutput.put("Body",Fournisseurs);
	      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
	 }
	
	} 
	public ResponseEntity < ? > getFournisseur(Long id) {
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		 try {
	Optional<Fournisseur> oFournisseur =  FournisseurDao.findById(id); 
	Fournisseur  fournisseur = oFournisseur.get(); 
	if(fournisseur!=null)
	{
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("Body",fournisseur);
		
	}
	else {
		
	         	new Exception();
	     }
    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);

		 }catch(Exception e)
		 {
			 respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "No founisseur exists with id "+id);
		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		 }
	
	
	}
	public ResponseEntity < ? > UpdateFournisseur(Fournisseur  ufournisseur,Long id)
	{
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();

		 try {
		Optional<Fournisseur> oFournisseur =  FournisseurDao.findById(id); 
		Fournisseur fournisseur = oFournisseur.get(); 
		fournisseur.setName(ufournisseur.getName());
		fournisseur.setAdresse(ufournisseur.getAdresse());
		fournisseur.setPhoneNum(ufournisseur.getPhoneNum());
		fournisseur.setAdresseMail(ufournisseur.getAdresseMail());
		 Fournisseur ff=FournisseurDao.save(fournisseur);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Updated Successfully!");
	    respJsonOutput.put("Body",ff);
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		 }
		 catch(NoSuchElementException e){
			 respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "Fournisseur not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			 
		 }
	}
	public ResponseEntity < ? > addFournisseur(Fournisseur fournisseur) {
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
	    Fournisseur ff=FournisseurDao.save( fournisseur);
	    respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Saved Successfully!");
	    respJsonOutput.put("Body",ff);
	    
	return new ResponseEntity < > (respJsonOutput, HttpStatus.CREATED);}
	public ResponseEntity < ? > deleteFournisseur(Long id) {
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		try {
		 FournisseurDao.deleteById(id);
		 respJsonOutput.put("status", 1);

		    respJsonOutput.put("message", "Record is Deleted Successfully!");
		    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		} catch(Exception e){
			respJsonOutput.clear();
			respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "Fournisseur not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		}
	}

}
