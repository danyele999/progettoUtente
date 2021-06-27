package it.daniele.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Utente;
import it.daniele.business.repository.UtenteRepo;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepo ut;

	public Utente crea(Utente utente) {
		return ut.save(utente);
	}

	public List<Utente> getAll() {
		List<Utente> lista = ut.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Utente cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return ut.findById(id).get();
		}
	}

	public Utente aggiorna(Utente utente) {
		if (utente.getCodice() == null) {
			return null;
		} else {
			return ut.save(utente);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			ut.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}
	
	public Utente trovaPerCognome(String cognome) {
		if(cognome == null) {
			return null;
		}else {
			return ut.findByCognome(cognome);
		}
	}
	
	public List<Utente> trovaPerDataDiNascita (Date data){
		if(data == null) {
			return null;
		}else {
			return ut.findByDataDiNascita(data);
		}
	}

}
