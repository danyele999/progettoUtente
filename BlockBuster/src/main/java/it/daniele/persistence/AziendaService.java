package it.daniele.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Azienda;
import it.daniele.business.repository.AziendaRepo;

@Service
public class AziendaService {

	@Autowired
	private AziendaRepo az;

	public Azienda crea(Azienda f) {
		return az.save(f);
	}

	public List<Azienda> getAll() {
		List<Azienda> lista = az.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Azienda cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return az.findById(id).get();
		}
	}

	public Azienda aggiorna(Azienda f) {
		if (f.getIdentity() == null) {
			return null;
		} else {
			return az.save(f);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			az.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public Azienda ricercaPerNome(String nome) {
		if (nome == null) {
			return null;
		} else {
			return az.findByname(nome);
		}
	}

	public List<Azienda> perData(Date durata) {

		if (durata == null) {
			return null;
		} else {
			return az.findByultimaModifica(durata);
		}

	}

}
