package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.SerieTv;
import it.daniele.business.Valutazione;
import it.daniele.business.repository.SerieTvRepo;

@Service
public class SerieTvService {

	@Autowired
	private SerieTvRepo serie;

	public SerieTv crea(SerieTv f) {
		return serie.save(f);
	}

	public List<SerieTv> getAll() {
		List<SerieTv> lista = serie.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public SerieTv cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return serie.findById(id).get();
		}
	}

	public SerieTv aggiorna(SerieTv f) {
		if (f.getId() == null) {
			return null;
		} else {
			return serie.save(f);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			serie.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public List<SerieTv> valutazione(String valuta) {
		if (valuta == null) {
			return null;
		} else {
			switch (valuta) {
			case "UNO":
				return serie.findByvalutazione(Valutazione.UNO);

			case "DUE":
				return serie.findByvalutazione(Valutazione.DUE);

			case "TRE":
				return serie.findByvalutazione(Valutazione.TRE);

			case "QUATTRO":
				return serie.findByvalutazione(Valutazione.QUATTRO);

			case "CINQUE":
				return serie.findByvalutazione(Valutazione.CINQUE);

			default:
				return null;

			}
		}
	}

	public SerieTv trovaDalNome(String nome) {
		if (nome == null) {
			return null;
		} else {
			return serie.findBynome(nome);
		}
	}

	public List<SerieTv> perValutazione(double val) {
		if (val <= 0) {
			return null;
		} else {
			return serie.trovaPerDurata(val);
		}
	}

}
