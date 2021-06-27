package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Film;
import it.daniele.business.Utente;
import it.daniele.business.Valutazione;
import it.daniele.business.repository.FilmRepo;
import it.daniele.business.repository.UtenteRepo;

@Service
public class FilmService {

	@Autowired
	private FilmRepo film;
	@Autowired
	private UtenteRepo ute;

	public Film crea(Film f) {
		return film.save(f);
	}

	public List<Film> getAll() {
		List<Film> lista = film.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Film cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return film.findById(id).get();
		}
	}

	public Film aggiorna(Film f) {
		if (f.getId() == null) {
			return null;
		} else {
			return film.save(f);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			film.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public Film ricercaPerNome(String nome) {
		if (nome == null) {
			return null;
		} else {
			Film ricerca = film.ricercaFilmPerNome(nome);
			return ricerca;
		}
	}

	public String prenota(String nomeF, String nomeU) {
		if (nomeF == null || nomeU == null) {
			return null;
		} else {
			Film desiderato = film.ricercaFilmPerNome(nomeF);
			Utente prenotante = ute.ricercaUtentePerNome(nomeU);
			verifica(desiderato.getId(), prenotante.getCodice());
			return "prenotazione avvenuta con successo";
		}
	}

	public String verifica(Long f, Long u) {
		Film fil = film.findById(f).get();
		Utente ut = ute.findById(u).get();
		if (fil.getUtente() != null) {
			return "il film e' gia' stato prenotato";
		} else {
			fil.setUtente(ut);
			film.save(fil);
			return "prenotazione avvenuta con successo";
		}

	}

	public List<Film> trovaPerDurata(double range) {
		if (range <= 0) {
			return null;
		} else {
			return film.trovaRange(range);
		}
	}

	public List<Film> valutazione(String valuta) {
		if (valuta == null) {
			return null;
		} else {
			switch (valuta) {
			case "UNO":
				return film.findByvalutazione(Valutazione.UNO);

			case "DUE":
				return film.findByvalutazione(Valutazione.DUE);

			case "TRE":
				return film.findByvalutazione(Valutazione.TRE);

			case "QUATTRO":
				return film.findByvalutazione(Valutazione.QUATTRO);

			case "CINQUE":
				return film.findByvalutazione(Valutazione.CINQUE);

			default:
				return null;

			}
		}
	}
}
