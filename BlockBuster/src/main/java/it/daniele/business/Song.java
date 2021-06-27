package it.daniele.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;
	private String songName;
	private double period;
	private Valutazione valutation;
	@ManyToOne
	private Utente userAssociated;

}
