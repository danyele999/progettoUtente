package it.daniele.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.daniele.business.Film;
import it.daniele.business.Valutazione;

public interface FilmRepo extends JpaRepository<Film, Long> {

	@Query("SELECT f FROM Film f WHERE f.nome LIKE %:nome%")
	Film ricercaFilmPerNome(String nome);

	@Query("SELECT f FROM Film f WHERE f.durata <= :range")
	List<Film> trovaRange(double range);

	List<Film> findByvalutazione(Valutazione dura);

}
