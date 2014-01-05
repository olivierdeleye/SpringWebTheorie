package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Filiaal;

public interface FiliaalDAO extends JpaRepository <Filiaal, Long>{
	Iterable<Filiaal> findByAdresPostcodeBetweenOrderByNaamAsc(int van, int tot); 
	Filiaal findByNaam(String naam);
	
}
