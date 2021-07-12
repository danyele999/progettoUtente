package it.daniele.presentation;

import java.lang.module.FindException;
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
import it.daniele.business.SerieTv;
import it.daniele.persistence.SerieTvService;

@RestController
@RequestMapping("/serieTv")
public class SerieTvController {
	@Autowired
	private SerieTvService serie;

	@PostMapping("/inserimento")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<SerieTv> crea(@RequestBody SerieTv f) {
		return new ResponseEntity<SerieTv>(serie.crea(f), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<SerieTv>> tutti() {
		if (serie.getAll().isEmpty()) {
			throw new FindException("la lista e' vuota");
		} else {
			return new ResponseEntity<List<SerieTv>>(serie.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiorna")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SerieTv> aggiorna(@RequestBody SerieTv f) {
		if (serie.aggiorna(f) == null) {
			throw new ModifierExeption("impossibile modificare questo oggetto");
		} else {
			return new ResponseEntity<SerieTv>(serie.aggiorna(f), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile eliminare l'oggetto con questo id");
		} else {
			return new ResponseEntity<String>(serie.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<SerieTv> trova(@PathVariable Long id) {
		if (serie.cercaId(id) == null) {
			throw new FindExeption("impossibile trovare un oggetto con questo id");
		} else {
			return new ResponseEntity<SerieTv>(serie.cercaId(id), HttpStatus.OK);
		}
	}

	@GetMapping("/ricerca/{valutazione}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> trovaPerValutazione(@PathVariable String valutazione) {
		return new ResponseEntity<>(serie.valutazione(valutazione), HttpStatus.OK);
	}
}
