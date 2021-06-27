package it.daniele.business.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.daniele.business.Utente;

public interface UtenteRepo extends JpaRepository<Utente, Long> {

	@Query("SELECT u FROM Utente u WHERE u.nome LIKE %:nome%")
	Utente ricercaUtentePerNome(String nome);

	Utente findByCognome(String cognome);

	List<Utente> findByDataDiNascita(Date dataDiNascita);

	Optional<Utente> findBynome(String username);

}
