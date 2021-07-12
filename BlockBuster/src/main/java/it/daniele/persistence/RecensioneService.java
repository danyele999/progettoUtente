package it.daniele.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Documentario;
import it.daniele.business.Film;
import it.daniele.business.SerieTv;
import it.daniele.business.repository.DocumentarioRepo;
import it.daniele.business.repository.FilmRepo;
import it.daniele.business.repository.SerieTvRepo;

@Service
public class RecensioneService {

	@Autowired
	private FilmRepo fr;
	@Autowired
	private DocumentarioRepo dr;
	@Autowired
	private SerieTvRepo sr;

	public String recensione(String oggetto, String recensione) {
		if (fr.ricercaFilmPerNome(oggetto) != null) {
			Film recensito = fr.ricercaFilmPerNome(oggetto);
			recensito.setRecensione(recensione);
			fr.save(recensito);
			return "recensione aggiunta al film";
		} else if (dr.findBynome(oggetto) != null) {
			Documentario doc = dr.findBynome(oggetto);
			doc.setRecensione(recensione);
			dr.save(doc);
			return "recensone aggiunta al documentario";
		} else if (sr.findBynome(oggetto) != null) {
			SerieTv serie = sr.findBynome(oggetto);
			serie.setRecensione(recensione);
			sr.save(serie);
			return "recensione aggiunta alla serie";
		} else
			return "oggetto da recensire non trovato";
	}

}
