package it.daniele.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.daniele.business.Documentario;
import it.daniele.business.Valutazione;

public interface DocumentarioRepo extends JpaRepository<Documentario, Long> {

	List<Documentario> findByvalutazione(Valutazione durata);

	Documentario findBynome(String nome);

	@Query("SELECT d FROM Documentario d WHERE d.durata <= :val")
	List<Documentario> trovaDouble(double val);

}
