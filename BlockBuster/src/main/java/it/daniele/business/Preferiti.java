package it.daniele.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Preferiti {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

}