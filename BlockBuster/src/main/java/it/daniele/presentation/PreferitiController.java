package it.daniele.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.business.Preferiti;
import it.daniele.persistence.PreferitiService;

@RestController
@RequestMapping("/preferiti")
public class PreferitiController {
	@Autowired
	private PreferitiService ps;

	@GetMapping("/crea")
	public ResponseEntity<?> crea(@RequestBody Preferiti pref) {
		if (pref == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(ps.crea(pref), HttpStatus.ACCEPTED);
		}
	}

	@PutMapping("/modifica")
	public ResponseEntity<?> modifica(@RequestBody Preferiti pref) {
		if (pref.getIdentify() < 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(ps.modifica(pref), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	public ResponseEntity<?> cancella(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<>(ps.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> cercaId(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(ps.cercaId(id), HttpStatus.OK);
		}

	}

	@GetMapping("/trovaTutti")
	public ResponseEntity<?> lista() {
		return new ResponseEntity<>(ps.lista(), HttpStatus.OK);
	}

}
