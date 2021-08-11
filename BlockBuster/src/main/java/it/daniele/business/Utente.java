package it.daniele.business;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.daniele.security.StringAttributeConverter;
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
	@Convert(converter = StringAttributeConverter.class)
	private String password;
	private String mail;
	private boolean active;
	private String indirizzo;
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
	@ManyToMany
	@JoinTable(name = "ees_utente_ruoli", joinColumns = @JoinColumn(name = "utente_codice"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
	private Set<Ruolo> roles = new HashSet<Ruolo>();
	@OneToMany(mappedBy = "utente")
	private List<Veicolo> veicoli;
	@OneToMany(mappedBy = "ute")
	private List<Garage> garage;


}
