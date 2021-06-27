package it.daniele.business;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Azienda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;
	private String name;
	private String via;
	private String telefono;
	private int numeroDipendenti;
	private Date ultimaModifica;
	@JsonIgnore
	@OneToMany(mappedBy = "azienda")
	private List<Utente> dipendenti;

}
