package it.daniele.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.daniele.business.SerieTv;
import it.daniele.business.Valutazione;

public interface SerieTvRepo extends JpaRepository<SerieTv, Long> {

	List<SerieTv> findByvalutazione(Valutazione val);

	SerieTv findBynome(String nome);

	@Query("SELECT s FROM SerieTv s WHERE s.durata <= :dura")
	List<SerieTv> trovaPerDurata(double dura);
	
	

}
