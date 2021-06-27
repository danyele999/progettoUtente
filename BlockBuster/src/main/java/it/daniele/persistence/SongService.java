package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Song;
import it.daniele.business.Valutazione;
import it.daniele.business.repository.SongRepo;

@Service
public class SongService {
	@Autowired
	private SongRepo sr;

	public Song crea(Song f) {
		return sr.save(f);
	}

	public List<Song> getAll() {
		List<Song> lista = sr.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Song cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return sr.findById(id).get();
		}
	}

	public Song aggiorna(Song f) {
		if (f.getIdentity() == null) {
			return null;
		} else {
			return sr.save(f);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			sr.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public List<Song> valutazione(String valuta) {
		if (valuta == null) {
			return null;
		} else {
			switch (valuta) {
			case "UNO":
				return sr.findByvalutation(Valutazione.UNO);

			case "DUE":
				return sr.findByvalutation(Valutazione.DUE);

			case "TRE":
				return sr.findByvalutation(Valutazione.TRE);

			case "QUATTRO":
				return sr.findByvalutation(Valutazione.QUATTRO);

			case "CINQUE":
				return sr.findByvalutation(Valutazione.CINQUE);

			default:
				return null;

			}
		}
	}

	public Song trovaDalNome(String nome) {
		if (nome == null) {
			return null;
		} else {
			return sr.findBysongName(nome);
		}
	}

}
