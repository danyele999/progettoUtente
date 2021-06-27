package it.daniele.presentation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.business.Utente;
import it.daniele.persistence.UtenteService;

@RestController
@RequestMapping("/utente")
public class UtenteController {
	@Autowired
	private UtenteService ut;

	@PostMapping("/inserimento")
	public ResponseEntity<Utente> crea(@RequestBody Utente utente) {
		return new ResponseEntity<Utente>(ut.crea(utente), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Utente>> tutti() {
		if (ut.getAll().isEmpty()) {
			return new ResponseEntity<List<Utente>>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<List<Utente>>(ut.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiornaUtente")
	public ResponseEntity<Utente> aggiorna(@RequestBody Utente utente) {
		if (ut.aggiorna(utente) == null) {
			return new ResponseEntity<Utente>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Utente>(ut.aggiorna(utente), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(ut.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	public ResponseEntity<Utente> trova(@PathVariable Long id) {
		if (ut.cercaId(id) == null) {
			return new ResponseEntity<Utente>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Utente>(ut.cercaId(id), HttpStatus.OK);
		}
	}

	@GetMapping("/ricercaCognome{cognome}")
	public ResponseEntity<Utente> trovaCognome(@PathVariable String cognome) {
		if (ut.trovaPerCognome(cognome) == null) {
			return new ResponseEntity<Utente>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Utente>(ut.trovaPerCognome(cognome), HttpStatus.OK);
		}
	}

	@GetMapping("/ricercaData{data}")
	public ResponseEntity<List<Utente>> trovaCognome(@PathVariable Date data) {
		if (ut.trovaPerDataDiNascita(data) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<>(ut.trovaPerDataDiNascita(data), HttpStatus.OK);
		}
	}
}
