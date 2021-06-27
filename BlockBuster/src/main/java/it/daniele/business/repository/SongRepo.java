package it.daniele.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daniele.business.Song;
import it.daniele.business.Valutazione;

public interface SongRepo extends JpaRepository<Song, Long> {

	List<Song> findByvalutation(Valutazione uno);

	Song findBysongName(String nome);

}
