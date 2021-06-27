package it.daniele.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Game {
	@Id
	private String gameName;
	@Enumerated(EnumType.STRING)
	private Type type;
	@Enumerated(EnumType.STRING)
	private Pegi age;
	private String descrizione;
	@OneToMany(mappedBy = "gioco")
	private List<Utente> utenti;
	@Enumerated(EnumType.STRING)
	private Valutazione valutation;

}
