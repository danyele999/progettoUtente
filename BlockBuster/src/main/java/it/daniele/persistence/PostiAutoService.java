package it.daniele.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.PostiAuto;
import it.daniele.business.Veicolo;
import it.daniele.business.repository.PostiAutoRepo;
import it.daniele.business.repository.VeicoloRepo;

@Service
public class PostiAutoService {

	@Autowired
	private PostiAutoRepo par;
	@Autowired
	private VeicoloRepo vr;

	public PostiAuto add(PostiAuto v) {
		return par.save(v);
	}

	public PostiAuto update(PostiAuto v) {
		if (v.getId() == null) {
			return null;
		} else
			return par.save(v);
	}

	public PostiAuto find(Long id) {
		if (id == null) {
			return null;
		} else {
			return par.findById(id).get();
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			par.deleteById(id);
			return "il posto auto e' stato eliminato correttamente";
		}
	}

	public String prenotazione(Long idMacchina, Long idPosto, double oraIngresso) {
		Veicolo daInserire = vr.findById(idMacchina).get();
		PostiAuto daPrenotare = par.findById(idPosto).get();
		if (daPrenotare.getVeicoli().size() < daPrenotare.getGarage().getNumeroPosti()) {
			if (daPrenotare.getDataPrenotazione() == null) {
				if (daPrenotare != null && oraIngresso > daPrenotare.getGarage().getOrarioApertura()) {
					daPrenotare.getVeicoli().add(daInserire);
					daPrenotare.setDataPrenotazione(LocalDate.now());
					par.save(daPrenotare);
					return " prenotazione fatta";
				}
			}

		}
		return "informazioni non corrette o posto gia' occupato, riprova";
	}

	public List<PostiAuto> getAll() {

		return par.findAll();
	}

}
