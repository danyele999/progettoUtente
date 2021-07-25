package it.daniele.presentation;

import java.lang.module.FindException;

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

import it.daniele.ExeptionHandler.CreationExeption;
import it.daniele.ExeptionHandler.DeleteExeption;
import it.daniele.business.PostiAuto;
import it.daniele.persistence.PostiAutoService;

@RestController
@RequestMapping("/postiAuto")
public class PostiAutoController {

	@Autowired
	private PostiAutoService pas;

	@PostMapping("/inserisci")
	public ResponseEntity<?> addPosto(@RequestBody PostiAuto v) {
		return new ResponseEntity<>(pas.add(v), HttpStatus.ACCEPTED);
	}

	@PutMapping("/aggiorna")
	public ResponseEntity<?> update(@RequestBody PostiAuto v) {
		if (v.getId() == null) {
			throw new CreationExeption(
					"impossibile trovare il posto da aggiornare, controlla l'id del posto e riprova");
		} else
			pas.add(v);
		return new ResponseEntity<>(pas.update(v), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile trovare un posot con questo id, controlla e riprova");
		} else {
			return new ResponseEntity<>(pas.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getPosto() {
		if (pas.getAll().isEmpty()) {
			throw new FindException("la lista e' vuota");
		} else
			return new ResponseEntity<>(pas.getAll(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/prenota/{idMacchina}/{idPosto}/{data}")
	public ResponseEntity<?> prenota(@PathVariable Long idMacchina, @PathVariable Long idPosto,
			@PathVariable double data) {
		return new ResponseEntity<>(pas.prenotazione(idMacchina, idPosto, data), HttpStatus.OK);
	}

}
