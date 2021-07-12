package it.daniele.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.business.Ruolo;
import it.daniele.persistence.RuoloService;

@RestController
@RequestMapping("/ruolo")
public class RuoloController {

	@Autowired
	private RuoloService rs;

	@GetMapping("/inserimento")
	public ResponseEntity<?> inserimentoNuovoRuolo(@RequestBody Ruolo role) {
		return new ResponseEntity<>(rs.aggiungiRuolo(role), HttpStatus.ACCEPTED);
	}

}
