package it.daniele.presentation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.ExeptionHandler.DeleteExeption;
import it.daniele.ExeptionHandler.FindExeption;
import it.daniele.ExeptionHandler.ModifierExeption;
import it.daniele.business.Utente;
import it.daniele.persistence.RecensioneService;
import it.daniele.persistence.UtenteService;

@RestController
@RequestMapping("/utente")
public class UtenteController {
	@Autowired
	private UtenteService ut;
	@Autowired
	private RecensioneService rs;

	@PostMapping("/inserimento")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Utente> crea(@RequestBody Utente utente) {
		return new ResponseEntity<Utente>(ut.crea(utente), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Utente>> tutti() {
		if (ut.getAll().isEmpty()) {
			throw new FindExeption("mi spiace ma la lista e' vuota");
		} else {
			return new ResponseEntity<List<Utente>>(ut.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiornaUtente")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Utente> aggiorna(@RequestBody Utente utente) {
		if (ut.aggiorna(utente) == null) {
			throw new ModifierExeption("impossibile modificare l'utente richiesto");
		} else {
			return new ResponseEntity<Utente>(ut.aggiorna(utente), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile eliminare questo utente");
		} else {
			return new ResponseEntity<String>(ut.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Utente> trova(@PathVariable Long id) {
		if (ut.cercaId(id) == null) {
			throw new FindExeption("nessun utente trovato con questo id");
		} else {
			return new ResponseEntity<Utente>(ut.cercaId(id), HttpStatus.OK);
		}
	}

	@GetMapping("/ricercaCognome{cognome}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Utente> trovaCognome(@PathVariable String cognome) {
		if (ut.trovaPerCognome(cognome) == null) {
			throw new FindExeption("nessun utente trovato con questo cognome");
		} else {
			return new ResponseEntity<Utente>(ut.trovaPerCognome(cognome), HttpStatus.OK);
		}
	}

	@GetMapping("/ricercaData{data}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Utente>> trovaCognome(@PathVariable Date data) {
		if (ut.trovaPerDataDiNascita(data) == null) {
			throw new FindExeption("nessuna data trovata per questa ricerca");
		} else {
			return new ResponseEntity<>(ut.trovaPerDataDiNascita(data), HttpStatus.OK);
		}
	}

	@GetMapping("/recensione")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> recensione(@RequestBody String nome, @RequestBody String recensione) {
		if (nome == null || recensione == null) {
			throw new FindExeption("nessun oggetto trovato");
		} else
			return new ResponseEntity<>(rs.recensione(nome, recensione), HttpStatus.OK);
	}
}
