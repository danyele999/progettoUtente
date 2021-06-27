package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Game;
import it.daniele.business.Valutazione;
import it.daniele.business.repository.GameRepo;

@Service
public class GameService {
	@Autowired
	private GameRepo gr;

	public Game crea(Game f) {
		return gr.save(f);
	}

	public List<Game> getAll() {
		List<Game> lista = gr.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Game cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return gr.findById(id).get();
		}
	}

	public Game aggiorna(Game f) {
		if (f.getGameName() == null) {
			return null;
		} else {
			return gr.save(f);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			gr.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public List<Game> valutazione(String valuta) {
		if (valuta == null) {
			return null;
		} else {
			switch (valuta) {
			case "UNO":
				return gr.findByvalutation(Valutazione.UNO);

			case "DUE":
				return gr.findByvalutation(Valutazione.DUE);

			case "TRE":
				return gr.findByvalutation(Valutazione.TRE);

			case "QUATTRO":
				return gr.findByvalutation(Valutazione.QUATTRO);

			case "CINQUE":
				return gr.findByvalutation(Valutazione.CINQUE);

			default:
				return null;

			}
		}
	}

	public Game trovaDalNome(String nome) {
		if (nome == null) {
			return null;
		} else {
			return gr.findBygameName(nome);
		}
	}

}
