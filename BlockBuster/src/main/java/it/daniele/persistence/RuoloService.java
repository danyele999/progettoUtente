package it.daniele.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Ruolo;
import it.daniele.business.repository.RuoloRepo;

@Service
public class RuoloService {

	@Autowired
	private RuoloRepo rr;

	public Ruolo aggiungiRuolo(Ruolo ruo) {
		if(ruo.getTipoRuolo() == null) {
			return null;
		}else
		return rr.save(ruo);
	}

	public String Rimuovi(Long id) {
		Ruolo daEliminare = rr.findById(id).get();
		rr.delete(daEliminare);
		return "il ruolo e' stato eliminato con successo";

	}

}
