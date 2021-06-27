package it.daniele.business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Preferiti {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long identify;
	@ManyToOne
	private Utente associatedUser;
	private Date insertDate;

}
