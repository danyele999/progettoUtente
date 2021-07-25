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
import it.daniele.business.Veicolo;
import it.daniele.persistence.VeicoloService;

@RestController
@RequestMapping("/veicolo")
public class VeicoloController {

	@Autowired
	private VeicoloService vs;

	@PostMapping("/inserisci")
	public ResponseEntity<?> addVeicle(@RequestBody Veicolo v) {
		return new ResponseEntity<>(vs.add(v), HttpStatus.ACCEPTED);
	}

	@PutMapping("/aggiorna")
	public ResponseEntity<?> update(@RequestBody Veicolo v) {
		if (v.getId() == null) {
			throw new CreationExeption(
					"impossibile trovare il veicolo da aggiornare, controlla l'id del veicolo e riprova");
		} else
			return new ResponseEntity<>(vs.update(v), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		if (vs.getAll().isEmpty()) {
			throw new FindException("la lista e' vuota");
		} else
			return new ResponseEntity<>(vs.getAll(), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile trovare un veicolo con questo id, controlla e riprova");
		} else {
			return new ResponseEntity<>(vs.delete(id), HttpStatus.ACCEPTED);
		}
	}
}
