package it.daniele.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daniele.business.Preferiti;

public interface PreferitiRepo extends JpaRepository<Preferiti, Long> {

	List<Preferiti> findByassociatedUserNome(String nome);

}
