package com.example.demo.Doa;

import com.example.demo.entities.Magasin;
import com.example.demo.entities.Voeux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {

	public Magasin findByName(String name);


}