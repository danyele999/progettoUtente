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
import it.daniele.business.Garage;
import it.daniele.persistence.GarageService;

@RestController
@RequestMapping("/Garage")
public class GarageController {

	@Autowired
	private GarageService gs;

	@PostMapping("/inserisci")
	public ResponseEntity<?> addGarage(@RequestBody Garage v) {
		return new ResponseEntity<>(gs.add(v), HttpStatus.ACCEPTED);
	}

	@PutMapping("/aggiorna")
	public ResponseEntity<?> update(@RequestBody Garage v) {
		if (v.getId() == null) {
			throw new CreationExeption(
					"impossibile trovare il garage da aggiornare, controlla l'id del garage e riprova");
		} else
			return new ResponseEntity<>(gs.update(v), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> cancella(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption("impossibile trovare un garage con questo id, controlla e riprova");
		} else {
			return new ResponseEntity<>(gs.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getVeicoli() {
		if (gs.getAll().isEmpty()) {
			throw new FindException("la lista e' vuota");
		} else
			return new ResponseEntity<>(gs.getAll(), HttpStatus.ACCEPTED);
	}

}
