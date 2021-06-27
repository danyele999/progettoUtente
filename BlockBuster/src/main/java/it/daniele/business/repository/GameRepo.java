package it.daniele.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daniele.business.Game;
import it.daniele.business.Valutazione;

public interface GameRepo extends JpaRepository<Game, Long> {

	Game findBygameName(String nome);

	List<Game> findByvalutation(Valutazione quattro);

}
