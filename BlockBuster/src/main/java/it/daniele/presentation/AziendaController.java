package it.daniele.presentation;

import java.lang.module.FindException;
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
import it.daniele.business.Azienda;
import it.daniele.persistence.AziendaService;

@RestController
@RequestMapping("/azienda")
public class AziendaController {
	@Autowired
	private AziendaService azser;

	@PostMapping("/inserimento")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Azienda> crea(@RequestBody Azienda f) {
		return new ResponseEntity<Azienda>(azser.crea(f), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Azienda>> tutti() {
		if (azser.getAll().isEmpty()) {
			throw new FindException("la lista e' vuota");
		} else {
			return new ResponseEntity<List<Azienda>>(azser.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiorna")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Azienda> aggiorna(@RequestBody Azienda f) {
		if (azser.aggiorna(f) == null) {
			throw new ModifierExeption("impossibile modificare questo oggetto");
		} else {
			return new ResponseEntity<Azienda>(azser.aggiorna(f), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile eliminare l'oggetto con questo id");
		} else {
			return new ResponseEntity<String>(azser.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Azienda> trova(@PathVariable Long id) {
		if (id == null) {
			throw new FindExeption("impossibile trovare un oggetto con questo id");
		} else {
			return new ResponseEntity<Azienda>(azser.cercaId(id), HttpStatus.OK);
		}
	}

	@GetMapping("/data/{data}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Azienda>> trova(@PathVariable Date data) {
		if (azser.perData(data) == null) {
			throw new FindExeption("impossibile trovare un oggetto con questo id");
		} else {
			return new ResponseEntity<List<Azienda>>(azser.perData(data), HttpStatus.OK);
		}
	}

	@GetMapping("/daniele/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> apiDiProva(@PathVariable Long id) {
		if (azser.ricercaPerCodice(id) == null) {
			throw new FindException("Antonio Daniele Crisci e' coglione");
		}
		return new ResponseEntity<>(azser.ricercaPerCodice(id), HttpStatus.OK);
	}
}
