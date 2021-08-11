package it.daniele.business.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daniele.business.Azienda;
import it.daniele.business.Utente;

public interface AziendaRepo extends JpaRepository<Azienda, Long> {

	Azienda findByname(String nome);

	List<Azienda> findByultimaModifica(Date data);

	Utente findBydipendentiCodice(Long codice);


}
