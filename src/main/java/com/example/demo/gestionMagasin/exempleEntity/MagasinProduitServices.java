package com.example.demo.gestionMagasin.exempleEntity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.demo.Doa.MagasinRepository;
import com.example.demo.Doa.ProduitRepository;
import com.example.demo.Doa.PropriteRepository;
import com.example.demo.entities.Magasin;
import com.example.demo.entities.Produit;
import com.example.demo.entities.Proprite;

@Service
public class MagasinProduitServices {
	@Autowired
	private ProduitRepository ProduitDao  ;
	@Autowired
	PropriteRepository PropriteDao ;
	@Autowired
	MagasinRepository MagasinDao;
	public ResponseEntity < ? > getAllProduits() {
		List<Produit> Produits =  ProduitDao.findAll(); 
		Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		if(!Produits.isEmpty())
		{
			respJsonOutput.put("status", 1);

		    respJsonOutput.put("message", "Record is Fetched Successfully!");
		    respJsonOutput.put("Body",Produits);
		    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
			
			
		}
		else {
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "No Produits exists ");

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		}
		
	} 
	public ResponseEntity < ? > getProduit(Long id) {
		Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
       try {
		Optional<Produit> oProduit =  ProduitDao.findById(id); 
		Produit produit = oProduit.get(); 
		if(produit!=null)
		{
			respJsonOutput.put("status", 1);

		    respJsonOutput.put("Body",produit);
		   
		}
		else {
			new Exception();
		}
		 return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		}catch(Exception e)
       {
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "No Produit exists with id: "+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
       }
		
	}
	public Collection<Proprite> getPropsofProduit(Long id) {
		Optional<Produit> oProduit =  ProduitDao.findById(id); 
		Produit produit = oProduit.get(); 
		return produit.getProprites();
	}
	public ResponseEntity < ? > UpdateProduit(Produit uproduit,Long id)
	{
		Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		 try {
		Optional<Produit> oProduit =  ProduitDao.findById(id); 
		Produit produit = oProduit.get(); 
		try{
		Magasin m=MagasinDao.findById(uproduit.getMagasin().getIdMagasin()).get();
		produit.setMagasin(m);
		}catch(Exception e){
			System.out.println(MagasinDao.count());
			System.out.println(produit.getMagasin().getName());
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "magasin not found with id"+uproduit.getMagasin().getIdMagasin());

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		}
		Produit pt=ProduitDao.findByRefStockable(uproduit.getRefStockable());
		if(pt!=null && pt.getIdStockable()!=id)
		{
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "Produit found with ref:"+uproduit.getRefStockable());

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		}
        produit.getProprites().forEach(p->PropriteDao.delete(p));	
		uproduit.getProprites().forEach(p->PropriteDao.save(p));
		produit.setProprites(uproduit.getProprites());
		produit.getProprites().forEach(p->p.setProduit(produit)); 
		produit.setName(uproduit.getName());
		produit.setTva(uproduit.getTva());	
		produit.setRefStockable(uproduit.getRefStockable());
		Produit pp=ProduitDao.save( produit); 
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Updated Successfully!");
	    respJsonOutput.put("Body",pp);
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		
		        
		
		 }
		 catch(NoSuchElementException e){
			  respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "produit not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
			 
		 }
	}
	public void addProprites(Proprite proprite ,Long id  )
	{
		 try {
				Optional<Produit> oProduit =  ProduitDao.findById(id); 
				Produit produit = oProduit.get(); 
				proprite.setProduit(produit);
				PropriteDao.save(proprite);
				ProduitDao.save(produit);
				
				
				 }
				 catch(NoSuchElementException e){
					 
					 
				 }
	}
	public ResponseEntity < ? > addProduit(Produit produit) {
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		try{
		Magasin m=MagasinDao.findById(produit.getMagasin().getIdMagasin()).get();
		produit.setMagasin(m);
		Produit pt=ProduitDao.findByRefStockable(produit.getRefStockable());
		if(pt!=null)
		{
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "Produit found with ref:"+produit.getRefStockable());

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		}
		produit.getProprites().forEach(p->PropriteDao.save(p));
		produit.getProprites().forEach(p->p.setProduit(produit));
		 Produit pp=ProduitDao.save( produit);           
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Saved Successfully!");
	    respJsonOutput.put("Body",pp);
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(MagasinDao.count());
			System.out.println(produit.getMagasin().getName());
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "magasin not found with id"+produit.getMagasin().getIdMagasin());

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		}
		
		  
	}
	public ResponseEntity < ? > deleteProduit(Long id)
	{
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();

		 try {
		ProduitDao.findById(id).get().getProprites().forEach(p->PropriteDao.delete(p));
		ProduitDao.deleteById(id);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Deleted Successfully!");
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		
		
	}
		 catch(NoSuchElementException e){
			 respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "produit not found with id:"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
			 
			 
		 }
	}
  


}
