package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.Doa.DemandeStockableRepository;
import com.example.demo.Doa.DemandeUnstockableRepository;
import com.example.demo.Doa.EmployerRepository;
import com.example.demo.Doa.FactureRepository;
import com.example.demo.Doa.FactureStockableRepository;
import com.example.demo.Doa.FournisseurRepository;
import com.example.demo.Doa.MagasinRepository;
import com.example.demo.Doa.ProduitRepository;
import com.example.demo.Doa.PropriteRepository;
import com.example.demo.Doa.ServiceRepository;
import com.example.demo.entities.Employer;
import com.example.demo.entities.Facture;
import com.example.demo.entities.FactureStockable;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Magasin;
import com.example.demo.entities.Produit;
import com.example.demo.entities.Proprite;
import com.example.demo.entities.Service;


@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {


		ApplicationContext ctx =SpringApplication.run(DemoApplication.class, args);
		ProduitRepository ProduitDao = ctx.getBean(ProduitRepository.class);
		ServiceRepository ServiceDao = ctx.getBean(ServiceRepository.class);
		FactureStockableRepository FactureStockableDao=  ctx.getBean(FactureStockableRepository.class);
		FactureRepository FactureDao = ctx.getBean(FactureRepository.class);
		FournisseurRepository  FournisseurDao =ctx.getBean(FournisseurRepository.class);
		 DemandeStockableRepository DemandeStockableDao =ctx.getBean(DemandeStockableRepository.class);  ;
		DemandeUnstockableRepository DemandeUnStockableDao = ctx.getBean(DemandeUnstockableRepository.class);
		EmployerRepository EmployerDao =ctx.getBean(EmployerRepository.class);  ;
 		Magasin magasin=new Magasin("mmmm");
 		Service s= new Service("s1");
 		s.setMagasin(magasin);
		MagasinRepository MagasinDao = ctx.getBean(MagasinRepository.class);
		MagasinDao.save(magasin);
		ServiceDao.save(s);
		PropriteRepository PropriteDao =ctx.getBean(PropriteRepository.class);
		Produit p1= new Produit("p1",50.0,magasin,"56565656");
		ProduitDao.save(p1);
		Fournisseur ff=new Fournisseur("ff1","zjjjjjjjzz",new Long(45666),"eeeee");
		FournisseurDao.save(ff); 
		Facture f1=new Facture(new Date(),"dddd",ff);
		FactureDao.save(f1);
		FournisseurDao.findAll().forEach(fff->System.out.println(fff.getName()));
		FactureStockable fs1= new FactureStockable(new Long(40),50.0,p1,f1);
		FactureStockableDao.save(fs1);
		FactureDao.findAll().forEach(f->System.out.println(f.getFournisseur()));
		PropriteDao.save(new Proprite("ddd","dede", p1));
		ProduitDao.save(new Produit("p2",70.0,magasin,"8878"));
		ProduitDao.save(new Produit("p3",30.0,magasin,"456"));
		Employer e=new Employer();
		e.setId(new Long(1));
		EmployerDao.save(e);
		ProduitDao.findAll().forEach(t->System.out.println(t.getName()));
		PropriteDao.findAll().forEach(t->System.out.println(t.getProduit().getName()));
		System.out.println("this is"+ProduitDao.findByRefStockable("56565656").getRefStockable());

	}


}


