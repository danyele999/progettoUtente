package it.daniele.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.ExeptionHandler.CreationExeption;
import it.daniele.ExeptionHandler.DeleteExeption;
import it.daniele.ExeptionHandler.FindExeption;
import it.daniele.ExeptionHandler.ModifierExeption;
import it.daniele.business.Preferiti;
import it.daniele.persistence.PreferitiService;

@RestController
@RequestMapping("/preferiti")
public class PreferitiController {
	@Autowired
	private PreferitiService ps;

	@GetMapping("/crea")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> crea(@RequestBody Preferiti pref) {
		if (pref == null) {
			throw new CreationExeption("impossibile creare questo oggetto");
		} else {
			return new ResponseEntity<>(ps.crea(pref), HttpStatus.ACCEPTED);
		}
	}

	@PutMapping("/modifica")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> modifica(@RequestBody Preferiti pref) {
		if (pref.getIdentify() < 0) {
			throw new ModifierExeption("impossibile modificare questo oggetto");
		} else {
			return new ResponseEntity<>(ps.modifica(pref), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile eliminare l'oggetto con questo id");
		} else {
			return new ResponseEntity<>(ps.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> cercaId(@PathVariable Long id) {
		if (id == null) {
			throw new FindExeption("impossibile trovare un oggetto con questo id");
		} else {
			return new ResponseEntity<>(ps.cercaId(id), HttpStatus.OK);
		}

	}

	@GetMapping("/trovaTutti")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> lista() {
		return new ResponseEntity<>(ps.lista(), HttpStatus.OK);
	}

}
