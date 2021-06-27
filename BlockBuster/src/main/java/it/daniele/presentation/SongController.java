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

import it.daniele.business.Song;
import it.daniele.persistence.SongService;

@RestController
@RequestMapping("/song")
public class SongController {
	@Autowired
	private SongService songserv;

	@PostMapping("/inserimento")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Song> crea(@RequestBody Song f) {
		return new ResponseEntity<Song>(songserv.crea(f), HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Song>> tutti() {
		if (songserv.getAll().isEmpty()) {
			return new ResponseEntity<List<Song>>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<List<Song>>(songserv.getAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/aggiorna")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Song> aggiorna(@RequestBody Song f) {
		if (songserv.aggiorna(f) == null) {
			return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Song>(songserv.aggiorna(f), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/cancella/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancella(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(songserv.delete(id), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/trova{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Song> trova(@PathVariable Long id) {
		if (songserv.cercaId(id) == null) {
			return new ResponseEntity<Song>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Song>(songserv.cercaId(id), HttpStatus.OK);
		}
	}
}
