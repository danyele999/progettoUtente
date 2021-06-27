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

import it.daniele.business.Game;
import it.daniele.persistence.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gameser;

	@PostMapping("/inserimento")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Game> crea(@RequestBody Game f) {
		return new ResponseEntity<Game>(gameser.crea(f), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Game>> tutti() {
		if (gameser.getAll().isEmpty()) {
			return new ResponseEntity<List<Game>>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<List<Game>>(gameser.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiorna")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Game> aggiorna(@RequestBody Game f) {
		if (gameser.aggiorna(f) == null) {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Game>(gameser.aggiorna(f), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(gameser.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Game> trova(@PathVariable Long id) {
		if (gameser.cercaId(id) == null) {
			return new ResponseEntity<Game>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Game>(gameser.cercaId(id), HttpStatus.OK);
		}
	}
}
