package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Preferiti;
import it.daniele.business.repository.PreferitiRepo;

@Service
public class PreferitiService {

	@Autowired
	private PreferitiRepo pr;

	public Preferiti crea(Preferiti pref) {
		return pr.save(pref);
	}

	public List<Preferiti> lista() {
		if (pr.findAll().isEmpty()) {
			return null;
		} else {
			return pr.findAll();
		}
	}

	public Preferiti modifica(Preferiti pref) {
		if (pref.getIdentify() < 0) {
			return null;
		} else {
			return pr.save(pref);
		}
	}

	public String delete(Long pref) {
		if (pref == null) {
			return null;
		} else {
			pr.deleteById(pref);
			return "eliminazione avvenuta con successo";
		}
	}

	public Preferiti cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return pr.findById(id).get();
		}
	}
	
	
}
