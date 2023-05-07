package com.example.demo.gestionMagasin.exempleEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader.BadVersionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Doa.FactureRepository;
import com.example.demo.Doa.FactureStockableRepository;
import com.example.demo.Doa.FournisseurRepository;
import com.example.demo.Doa.ProduitRepository;
import com.example.demo.entities.Facture;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Produit;


@Service
public class MagasinFactureServices {
	@Autowired
	private FactureRepository FactureDao  ;
	@Autowired
	private FournisseurRepository FournisseurDao  ;
	@Autowired
	private ProduitRepository ProduitDao  ;
	@Autowired
	private FactureStockableRepository FactureStockableDao  ;
	public ResponseEntity < ? > getAllFactures() {
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
	List<Facture> Factures =  FactureDao.findAll(); 
	if(!Factures.isEmpty())
	{
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Records fetched Successfully!");
	    respJsonOutput.put("Body",Factures );

	    
	return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		
	}
	else {
		respJsonOutput.clear();

	      respJsonOutput.put("status", 0);

	      respJsonOutput.put("message", "There is No Factures Data exists ");
	      respJsonOutput.put("Body",Factures );

	      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		
	}
	} 
	public ResponseEntity < ? > getFacture(Long id) {
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
	
		try {
	Optional<Facture> oFacture =  FactureDao.findById(id); 
	Facture  facture = oFacture.get(); 
	
	 
	if(facture!=null)
	{
		respJsonOutput.put("status", 1);

	    
	    respJsonOutput.put("Body",facture );

	    
	  
		
	}
	else {
		
	      

	      new Exception();
		
	}
	  return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		}
	catch(Exception e) {
		
		respJsonOutput.clear();

	      respJsonOutput.put("status", 0);

	      respJsonOutput.put("message", "There is No facture with id : "+id);

	      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		
	}
	}
	public ResponseEntity < ? > UpdateFacture(Facture  ufacture,Long id)
	{
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		 try {
		Optional<Facture> oFacture =  FactureDao.findById(id); 
		Facture  facture = oFacture.get(); 
		try{
			ufacture.getFactureStockables().forEach(ff->{
				respJsonOutput.clear();
			      respJsonOutput.put("ff",ff.getStockable().getIdStockable() );
			Produit p=ProduitDao.findById(ff.getStockable().getIdStockable()).get();		
		
				if(p.getRefStockable().equals(ff.getStockable().getRefStockable()))
				{
			
				}
				else {
					respJsonOutput.clear();

				      respJsonOutput.put("status", 0);

				      respJsonOutput.put("message", "produit ref and id are not compatible");

				      throw new RuntimeException("produit ref and id are not compatible");
				}
			
			
				
			});
			}catch(Exception e)
			{
				if(e.getMessage().equals("No value present")){

					String s="produit not found with id:"+respJsonOutput.get("ff");
					respJsonOutput.clear();
				      respJsonOutput.put("status", 0);

				      respJsonOutput.put("message", s);
				}
				
				
				
				return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			}
		try{
			Fournisseur f= FournisseurDao.findById(ufacture.getFournisseur().getIdFournisseur()).get();

			}
			catch(Exception e){
				respJsonOutput.clear();

			      respJsonOutput.put("status", 0);

			      respJsonOutput.put("message", "fournisseur not found with id : "+ufacture.getFournisseur().getIdFournisseur());

			      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			}
		facture.getFactureStockables().forEach(ff->{
			ff.getStockable().getFactureStockables().remove(ff);
			ff.setStockable(null);
			
		});
		

		facture.getFactureStockables().forEach(ff->FactureStockableDao.delete(ff));
	
		ufacture.getFactureStockables().forEach(ff->{
			respJsonOutput.clear();
		      respJsonOutput.put("ff",ff.getStockable().getIdStockable() );
		Produit p=ProduitDao.findById(ff.getStockable().getIdStockable()).get();		
	
			if(p.getRefStockable().equals(ff.getStockable().getRefStockable()))
			{
			p.getFactureStockables().add(ff);
			p.setQuantite(ff.getQuantite());
			ff.setStockable(p);
			}
		
		
			
		});
		
		ufacture.getFactureStockables().forEach(ff->FactureStockableDao.save(ff));
		facture.setFactureStockables(ufacture.getFactureStockables());
		facture.getFactureStockables().forEach(ff->ff.setFacture(facture));
		
		Fournisseur f= FournisseurDao.findById(ufacture.getFournisseur().getIdFournisseur()).get();
			facture.setFournisseur(f);

		
		facture.setAddressFacturation(ufacture.getAddressFacturation());
		facture.setDateFacturation(ufacture.getDateFacturation());
		Facture ffe=FactureDao.save(facture);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Updated Successfully!");
	    respJsonOutput.put("Body",ffe);
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		 }
		 catch(Exception e){
			 System.out.println("eeeeeeeeeee"+e.getMessage());
			 respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "facture not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		 }
	}
	public ResponseEntity < ? > addFacture(Facture  facture) {
		System.out.println(facture);
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
	    try {
		Fournisseur f= FournisseurDao.findById(facture.getFournisseur().getIdFournisseur()).get();
		try {
			facture.getFactureStockables().forEach(ff->{
				respJsonOutput.clear();
			      respJsonOutput.put("ff",ff.getStockable().getIdStockable() );
				Produit p=ProduitDao.findById(ff.getStockable().getIdStockable()).get();
				
					
					if(p.getRefStockable().equals(ff.getStockable().getRefStockable()))
						
					{
					
					}
					else {
						
						respJsonOutput.clear();

					      respJsonOutput.put("status", 0);

					      respJsonOutput.put("message", "produit ref and id are not compatible");
                              throw new RuntimeException("produit ref and id are not compatible");
					}
					
			
				});
		
		
			facture.getFactureStockables().forEach(ff->{
				respJsonOutput.clear();
			      respJsonOutput.put("ff",ff.getStockable().getIdStockable() );
				Produit p=ProduitDao.findById(ff.getStockable().getIdStockable()).get();
				
					
					if(p.getRefStockable().equals(ff.getStockable().getRefStockable()))
						
					{
					p.getFactureStockables().add(ff);
					p.setQuantite(ff.getQuantite());
					ff.setStockable(p);
					}
					
					
			
				});
			facture.setFournisseur(f);
			facture.getFactureStockables().forEach(ff->FactureStockableDao.save(ff));
			facture.getFactureStockables().forEach(ff->ff.setFacture(facture));
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			if(e.getMessage().equals("No value present")){

				String s="produit not found with id:"+respJsonOutput.get("ff");
				respJsonOutput.clear();
			      respJsonOutput.put("status", 0);

			      respJsonOutput.put("message", s);
			}
			
			System.out.println(e.getMessage());
			System.out.println(respJsonOutput.get("message"));
			
			return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		}
		
			
			
		}
		catch(Exception e) {

			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "fournisseur not found with Id"+facture.getFournisseur().getIdFournisseur());

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		}
		System.out.println(respJsonOutput.get("message"));

		 Facture ffe=FactureDao.save( facture);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Saved Successfully!");
	    respJsonOutput.put("Body",ffe);

	    
	return new ResponseEntity < > (respJsonOutput, HttpStatus.CREATED);}
	public ResponseEntity < ? > deleteFacture(Long id) {
	    Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();

		 try {
			 Facture ffe=FactureDao.findById(id).get();
		FactureDao.findById(id).get().getFactureStockables().forEach(ff->{
			ff.getStockable().getFactureStockables().remove(ff);
			ff.setStockable(null);
			
		});
		FactureDao.findById(id).get().getFactureStockables().forEach(ff->FactureStockableDao.delete(ff));
		FactureDao.deleteById(id);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Deleted Successfully!");
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
	    
	}
		 catch(Exception e){
			 System.out.println("eeeeeeeeeee"+e.getMessage());
			 respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "facture not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		 }
	}



}
