package it.daniele.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String via;
	private int numeroPosti;
	private double orarioApertura = 8.30;
	private double orarioChiusura = 22.30;
	@OneToMany(mappedBy = "garage")
	private List<PostiAuto> posti;
	@ManyToOne
	private Utente ute;

}
