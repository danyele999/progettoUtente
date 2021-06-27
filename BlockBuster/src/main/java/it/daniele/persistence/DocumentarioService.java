package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Documentario;
import it.daniele.business.Valutazione;
import it.daniele.business.repository.DocumentarioRepo;

@Service
public class DocumentarioService {

	@Autowired
	private DocumentarioRepo doc;

	public Documentario crea(Documentario f) {
		return doc.save(f);
	}

	public List<Documentario> getAll() {
		List<Documentario> lista = doc.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Documentario cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return doc.findById(id).get();
		}
	}

	public Documentario aggiorna(Documentario f) {
		if (f.getId() == null) {
			return null;
		} else {
			return doc.save(f);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			doc.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public Documentario ricercaPerNome(String nome) {
		if (nome == null) {
			return null;
		} else {
			return doc.findBynome(nome);
		}
	}

	public List<Documentario> perDurata(double durata) {

		if (durata <= 0) {
			return null;
		} else {
			return doc.trovaDouble(durata);
		}

	}

	public List<Documentario> valutazione(String valuta) {
		if (valuta == null) {
			return null;
		} else {
			switch (valuta) {
			case "UNO":
				return doc.findByvalutazione(Valutazione.UNO);

			case "DUE":
				return doc.findByvalutazione(Valutazione.DUE);

			case "TRE":
				return doc.findByvalutazione(Valutazione.TRE);

			case "QUATTRO":
				return doc.findByvalutazione(Valutazione.QUATTRO);

			case "CINQUE":
				return doc.findByvalutazione(Valutazione.CINQUE);

			default:
				return null;

			}
		}
	}
}
