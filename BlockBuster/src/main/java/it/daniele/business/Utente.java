package it.daniele.business;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codice;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String nickname;
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "utente")
	private List<Film> film;
	@JsonIgnore
	@OneToMany(mappedBy = "utente")
	private List<Documentario> documentario;
	@JsonIgnore
	@OneToMany(mappedBy = "utente")
	private List<SerieTv> serieTv;
	@JsonIgnore
	@OneToMany(mappedBy = "associatedUser")
	private List<Preferiti> preferiti;
	@ManyToOne
	private Game gioco;
	@JsonIgnore
	@OneToMany(mappedBy = "userAssociated")
	private List<Song> musica;
	@ManyToOne
	private Azienda azienda;

}
