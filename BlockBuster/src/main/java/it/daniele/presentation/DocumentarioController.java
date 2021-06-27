package it.daniele.presentation;

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

import it.daniele.business.Documentario;
import it.daniele.persistence.DocumentarioService;

@RestController
@RequestMapping("/documentario")
public class DocumentarioController {
	@Autowired
	private DocumentarioService docu;

	@PostMapping("/inserimento")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Documentario> crea(@RequestBody Documentario f) {
		return new ResponseEntity<Documentario>(docu.crea(f), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Documentario>> tutti() {
		if (docu.getAll().isEmpty()) {
			return new ResponseEntity<List<Documentario>>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<List<Documentario>>(docu.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiorna")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Documentario> aggiorna(@RequestBody Documentario f) {
		if (docu.aggiorna(f) == null) {
			return new ResponseEntity<Documentario>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Documentario>(docu.aggiorna(f), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(docu.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Documentario> trova(@PathVariable Long id) {
		if (docu.cercaId(id) == null) {
			return new ResponseEntity<Documentario>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Documentario>(docu.cercaId(id), HttpStatus.OK);
		}
	}
}
