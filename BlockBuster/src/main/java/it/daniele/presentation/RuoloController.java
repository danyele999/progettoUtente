package it.daniele.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.ExeptionHandler.CreationExeption;
import it.daniele.ExeptionHandler.DeleteExeption;
import it.daniele.business.Ruolo;
import it.daniele.persistence.RuoloService;

@RestController
@RequestMapping("/ruolo")
public class RuoloController {

	@Autowired
	private RuoloService rs;

	@GetMapping("/inserimento")
	public ResponseEntity<?> inserimentoNuovoRuolo(@RequestBody Ruolo role) {
		if (rs.aggiungiRuolo(role).getClass() == null) {
			throw new CreationExeption("i dati inseriti non sono supportati");
		} else
			return new ResponseEntity<>(rs.aggiungiRuolo(role), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/cancellaRuolo/{id}")
	public ResponseEntity<?> cancellazioneRuolo(@PathVariable Long id) {
		if (id == null) {
			throw new DeleteExeption(
					"l'id che si sta cercando di eliminare non e' stato trovato. controllare e riprovare");
		} else
			return new ResponseEntity<>(rs.Rimuovi(id), HttpStatus.ACCEPTED);
	}

}
