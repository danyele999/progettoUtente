package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.daniele.business.Veicolo;
import it.daniele.business.repository.VeicoloRepo;

@Service
public class VeicoloService {

	@Autowired
	private VeicoloRepo vr;

	public Veicolo add(Veicolo v) {
		return vr.save(v);
	}

	public Veicolo update(Veicolo v) {
		if (v.getId() == null) {
			return null;
		} else
			return vr.save(v);
	}

	public Veicolo find(Long id) {
		if (id == null) {
			return null;
		} else {
			return vr.findById(id).get();
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			vr.deleteById(id);
			return "il veicolo e' stato eliminato correttamente";
		}
	}

	public List<Veicolo> getAll() {
		if (vr.findAll().isEmpty()) {
			return null;
		} else
			return vr.findAll();
	}

}
