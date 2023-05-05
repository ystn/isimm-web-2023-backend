package com.example.demo.gestionMagasin.exempleEntity;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Doa.EmployerRepository;
import com.example.demo.entities.DemandeStockable;
import com.example.demo.entities.Employer;
import com.example.demo.entities.Service;

public class MagasinDemandeStockableMapper {
	
     public static MagasinDemandeStockableDTO mapToMagasinDemandeStockableDTO(DemandeStockable demandeStockable,String source)
     {
    	
    		// MagasinDemandeStockableDTO ddto=new MagasinDemandeStockableDTO(demandeStockable.getDescription(),demandeStockable.getEtat(),demandeStockable.getDemandeUnStockables(),demandeStockable.getService(),demandeStockable.getEmployer(),source);
    		 return null;
    	 
    	 
     }
     public static DemandeStockable mapToDemandeStockable(MagasinDemandeStockableDTO demandeStockableDTO)
     {
    	 DemandeStockable d= new DemandeStockable(demandeStockableDTO.getDescription(),demandeStockableDTO.getEtat());
    	 if(demandeStockableDTO.getSource().equals("service"))
    	 {
    		 d.setDemandeUnStockables(demandeStockableDTO.getDemandeUnStockables());
    		 Service s =new Service();
    		 s.setIdService(demandeStockableDTO.getService().getIdService());
    		 d.setEmployer(null);
    		 d.setService(s);
    		
    	 }
    	 if(demandeStockableDTO.getSource().equals("employer"))
    	 {
    		 d.setDemandeUnStockables(demandeStockableDTO.getDemandeUnStockables());
    		 Employer e= new Employer();
    		 e.setId(demandeStockableDTO.getEmployer().getId());
    		 d.setEmployer(e);
    		 d.setService(null);
    		 
    	 }
		 return d;

    	 
    	 
     }
}
