package it.daniele.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daniele.business.Veicolo;

public interface VeicoloRepo extends JpaRepository<Veicolo, Long> {

	

}
