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

import com.example.demo.Doa.DemandeStockableRepository;
import com.example.demo.Doa.DemandeUnstockableRepository;
import com.example.demo.Doa.EmployerRepository;
import com.example.demo.Doa.ProduitRepository;
import com.example.demo.Doa.ServiceRepository;
import com.example.demo.entities.DemandeStockable;
import com.example.demo.entities.DemandeStockable.Etat;
import com.example.demo.entities.Employer;
import com.example.demo.entities.Facture;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Produit;




@Service
public class MagasinDemandeStockableServices {
	
	@Autowired
	private DemandeStockableRepository DemandeStockableDao  ;
	@Autowired
	private ProduitRepository ProduitDao  ;
	@Autowired
	private DemandeUnstockableRepository DemandeUnStockableDao  ;
	@Autowired
	private ServiceRepository ServiceDao  ;
	@Autowired
	private EmployerRepository EmployerDao  ;
	public ResponseEntity < ? > getAllDemandeStockables() {
		Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		List<DemandeStockable> DemandeStockables =  DemandeStockableDao.findAll(); 
		List<DemandeStockable> DemandeStockablesService=DemandeStockables.stream().filter(d->d.getService()!=null).toList();
		if(!DemandeStockablesService.isEmpty())
		{
			respJsonOutput.put("status", 1);

		    respJsonOutput.put("message", "Records fetched Successfully!");
		    respJsonOutput.put("Body",DemandeStockablesService );

		    
		return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
			
		}
		else {
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "There is No Demandes Data exists ");
		      respJsonOutput.put("Body",DemandeStockablesService );

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		}
	
	} 
	public ResponseEntity < ? > getDemandeStockables( Long id) {
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
			
			try {
				Optional<DemandeStockable> oDemandeStockable =  DemandeStockableDao.findById(id); 
				DemandeStockable  demandeStockable = oDemandeStockable.get(); 
		
		 
		if(demandeStockable!=null)
		{
			respJsonOutput.put("status", 1);

		    
		    respJsonOutput.put("Body",demandeStockable );

		    
		  
			
		}
		else {
			
		      

		      new Exception();
			
		}
		  return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
			}
		catch(Exception e) {
			
			respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "There is No demande with id : "+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		}
	
	}
	public ResponseEntity < ? > addDemandeStockable( DemandeStockable  demandeStockable) {
		Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
		try {
	    	
			 demandeStockable.getDemandeUnStockables().forEach(dd->{
				 respJsonOutput.clear();
			      respJsonOutput.put("dd",dd.getStockable().getIdStockable() );
					Produit p=ProduitDao.findById(dd.getStockable().getIdStockable()).get();
					
					
					
						if(demandeStockable.getEtat()==Etat.approved)
						{
							if(p.getQuantite()>=dd.getQuantite()  ) {
							
							}
							else {
								  throw new RuntimeException("produit quantite inferieur au demande quantite pour produit"+dd.getStockable().getIdStockable());
							}
						}
					
					
						
						
					
						
			 });
			 try {
			 if(demandeStockable.getService()!=null)
			 {
				 com.example.demo.entities.Service s=ServiceDao.findById(demandeStockable.getService().getIdService()).get();
				 
			 }
			 }catch(Exception e){
					respJsonOutput.clear();

				      respJsonOutput.put("status", 0);

				      respJsonOutput.put("message", "service not found with id : "+demandeStockable.getService().getIdService());

				      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
				}
				try{
			          if(demandeStockable.getEmployer()!=null)
			 {
			        	  
				 Employer e=EmployerDao.findById( demandeStockable.getEmployer().getId()).get();
				
			 }
				}
				catch(Exception e){
					respJsonOutput.clear();

				      respJsonOutput.put("status", 0);

				      respJsonOutput.put("message", "employer not found with id : "+demandeStockable.getEmployer().getId());

				      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
				}
			 
		}catch(Exception e)
		{
			if(e.getMessage().equals("No value present")){
			String s="produit not found with id:"+respJsonOutput.get("dd");
			respJsonOutput.clear();
		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", s);
			}
			if(e.getMessage().equals("produit quantite inferieur au demande quantite"))
			{
				respJsonOutput.clear();
			      respJsonOutput.put("status", 0);

			      respJsonOutput.put("message", e.getMessage());
			}
			System.out.println(e.getMessage());
			return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);

			
		}
	   
	    	
			 demandeStockable.getDemandeUnStockables().forEach(dd->{
				 respJsonOutput.clear();
			      respJsonOutput.put("dd",dd.getStockable().getIdStockable() );
					Produit p=ProduitDao.findById(dd.getStockable().getIdStockable()).get();
					
					
						if(demandeStockable.getEtat()==Etat.approved)
						{
							if(p.getQuantite()>=dd.getQuantite()  ) {
							p.setQuantite(p.getQuantite()-dd.getQuantite());
							}
							
						}
						p.getDemandes().add(dd);
						dd.setStockable(p);
					
					
					
						
						
					
						
			 });
			
			 if(demandeStockable.getService()!=null)
			 {
				 com.example.demo.entities.Service s=ServiceDao.findById(demandeStockable.getService().getIdService()).get();
				 s.addDemandeStockable(demandeStockable);
				 demandeStockable.setService(s);
			 }
			 
				
			          if(demandeStockable.getEmployer()!=null)
			 {
			        	  
				 Employer e=EmployerDao.findById( demandeStockable.getEmployer().getId()).get();
				 e.getDemandeStockable().add(demandeStockable);
				 demandeStockable.setEmployer(e);
			 }
				
			 demandeStockable.getDemandeUnStockables().forEach(dd->DemandeUnStockableDao.save(dd));
			 demandeStockable.getDemandeUnStockables().forEach(dd->dd.setDemandeStockable(demandeStockable));
			
		
			
		
	    DemandeStockable dde=DemandeStockableDao.save(demandeStockable);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Saved Successfully!");
	    respJsonOutput.put("Body",dde);

	    
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.CREATED);
		
	 }
	public ResponseEntity < ? > UpdateDemandeStockable( DemandeStockable  udemandeStockable, Long id)
	{
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();
            try {
	
		
		 Optional<DemandeStockable> oDemandeStockable =  DemandeStockableDao.findById(id); 
			DemandeStockable  demandeStockable = oDemandeStockable.get();
			 try {
					udemandeStockable.getDemandeUnStockables().forEach(dd->{
						respJsonOutput.clear();
					      respJsonOutput.put("dd",dd.getStockable().getIdStockable() );
					Produit p=ProduitDao.findById(dd.getStockable().getIdStockable()).get();	
					
						if(udemandeStockable.getEtat()==Etat.approved)
						{
							if(p.getQuantite()>=dd.getQuantite()  ) {
							}
							else {
								  throw new RuntimeException("produit quantite inferieur au demande quantite");
							}
						}
						
					
					
					});
				 }catch(Exception e)
					{
						if(e.getMessage().equals("No value present")){
						String s="produit not found with id:"+respJsonOutput.get("dd");
						respJsonOutput.clear();
					      respJsonOutput.put("status", 0);

					      respJsonOutput.put("message", s);
						}
						if(e.getMessage().equals("produit quantite inferieur au demande quantite"))
						{
							respJsonOutput.clear();
						      respJsonOutput.put("status", 0);

						      respJsonOutput.put("message", e.getMessage());
						}
						System.out.println(e.getMessage());
						return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);

						
					}
			 try{
					if(demandeStockable.getService()!=null)
					 {
						 System.out.println("ssssssefefefefefefege"+udemandeStockable.getService().getIdService());
						 com.example.demo.entities.Service s=ServiceDao.findById(udemandeStockable.getService().getIdService()).get();
						
					 }

				}
				catch(Exception e){
					respJsonOutput.clear();

				      respJsonOutput.put("status", 0);

				      respJsonOutput.put("message", "service not found with id : "+udemandeStockable.getService().getIdService());

				      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
				}
				try{
					if(demandeStockable.getEmployer()!=null)
					 {
						 Employer e=EmployerDao.findById( udemandeStockable.getEmployer().getId()).get();
						
					 }
					

				}
				catch(Exception e){
					respJsonOutput.clear();

				      respJsonOutput.put("status", 0);

				      respJsonOutput.put("message", "employer not found with id : "+udemandeStockable.getEmployer().getId());

				      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
				}
			demandeStockable.getDemandeUnStockables().forEach(dd->{
			dd.getStockable().getDemandes().remove(dd);
			dd.setStockable(null);
			
		});
		

	 demandeStockable.getDemandeUnStockables().forEach(dd->DemandeUnStockableDao.delete(dd));
		udemandeStockable.getDemandeUnStockables().forEach(dd->{
		Produit p=ProduitDao.findById(dd.getStockable().getIdStockable()).get();		
		
			p.getDemandes().add(dd);
			dd.setStockable(p);
		});
		udemandeStockable.getDemandeUnStockables().forEach(dd->DemandeUnStockableDao.save(dd));
		demandeStockable.setDemandeUnStockables(udemandeStockable.getDemandeUnStockables());
		demandeStockable.getDemandeUnStockables().forEach(dd->dd.setDemandeStockable(demandeStockable));
		
			if(demandeStockable.getService()!=null)
			 {
				 com.example.demo.entities.Service s=ServiceDao.findById(udemandeStockable.getService().getIdService()).get();
				 s.addDemandeStockable(demandeStockable);
				 demandeStockable.setService(s);
			 }

		
		
			if(demandeStockable.getEmployer()!=null)
			 {
				 Employer e=EmployerDao.findById( udemandeStockable.getEmployer().getId()).get();
				 e.getDemandeStockable().add(demandeStockable);
				 demandeStockable.setEmployer(e);
			 }
			

		
		demandeStockable.setDescription(udemandeStockable.getDescription());
		demandeStockable.setEtat(udemandeStockable.getEtat());
		 DemandeStockable dde=DemandeStockableDao.save(demandeStockable);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Updated Successfully!");
	    respJsonOutput.put("Body",dde);
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
		 }
		 catch(Exception e){
			 System.out.println("eeeeeeeeeee"+e.getMessage());
			 respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "demande not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
		 }
		
		
		
		
	}
	public ResponseEntity < ? > deleteDemandeStockable( Long id) {
		 Map < String, Object > respJsonOutput = new LinkedHashMap < String, Object > ();

		 try {
			 DemandeStockable dde=DemandeStockableDao.findById(id).get();
			 DemandeStockableDao.findById(id).get().getDemandeUnStockables().forEach(dd->{
			dd.getStockable().getDemandes().remove(dd);
			dd.setStockable(null);
			
		});
			 DemandeStockableDao.findById(id).get().getDemandeUnStockables().forEach(dd->DemandeUnStockableDao.delete(dd));
			 if(dde.getService()!=null)
			 {
				 DemandeStockableDao.findById(id).get().getService().getDemandeStockables().remove(dde);
			 }
			 if(dde.getEmployer()!=null)
			 {
				 DemandeStockableDao.findById(id).get().getEmployer().getDemandeStockable().remove(dde);

			 }
			 DemandeStockableDao.deleteById(id);
		respJsonOutput.put("status", 1);

	    respJsonOutput.put("message", "Record is Deleted Successfully!");
	    return new ResponseEntity < > (respJsonOutput, HttpStatus.OK);
	    
	}
		 catch(Exception e){
			 System.out.println("eeeeeeeeeee"+e.getMessage());
			 respJsonOutput.clear();

		      respJsonOutput.put("status", 0);

		      respJsonOutput.put("message", "demande not found with id"+id);

		      return new ResponseEntity < > (respJsonOutput, HttpStatus.NOT_FOUND);
			
		 }
	}

			
    
}
