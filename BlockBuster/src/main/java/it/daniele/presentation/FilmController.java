package it.daniele.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.business.Film;
import it.daniele.persistence.FilmService;

@RestController
@RequestMapping("/film")
public class FilmController {
	@Autowired
	private FilmService filser;

	@PostMapping("/inserimento")
	public ResponseEntity<Film> crea(@RequestBody Film f) {
		return new ResponseEntity<Film>(filser.crea(f), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Film>> tutti() {
		if (filser.getAll().isEmpty()) {
			return new ResponseEntity<List<Film>>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<List<Film>>(filser.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiorna")
	public ResponseEntity<Film> aggiorna(@RequestBody Film f) {
		if (filser.aggiorna(f) == null) {
			return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Film>(filser.aggiorna(f), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(filser.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	public ResponseEntity<Film> trova(@PathVariable Long id) {
		if (filser.cercaId(id) == null) {
			return new ResponseEntity<Film>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Film>(filser.cercaId(id), HttpStatus.OK);
		}
	}

	@PutMapping("/prenota/{utente}/{film}")
	public ResponseEntity<?> prenotazione(@PathVariable String utente, @PathVariable String film) {
		return new ResponseEntity<>(filser.prenota(utente, film), HttpStatus.OK);
	}
}
