package it.daniele.business;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class SerieTv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private double durata;
	private String recensione;
	@Enumerated(EnumType.STRING)
	private Valutazione valutazione;
	@Enumerated(EnumType.STRING)
	private Genere genere;
	@ManyToOne
	private Utente utente;

}
