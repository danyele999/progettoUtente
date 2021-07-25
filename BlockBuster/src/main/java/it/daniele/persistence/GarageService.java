package it.daniele.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.daniele.business.Garage;
import it.daniele.business.repository.GarageRepo;

@Service
public class GarageService {

	@Autowired
	private GarageRepo garageRepo;
	

	public Garage add(Garage v) {
		return garageRepo.save(v);
	}

	public Garage update(Garage v) {
		if (v.getId() == null) {
			return null;
		} else
			return garageRepo.save(v);
	}

	public Garage find(Long id) {
		if (id == null) {
			return null;
		} else {
			return garageRepo.findById(id).get();
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			garageRepo.deleteById(id);
			return "il Garage e' stato eliminato correttamente";
		}
	}

	public List<Garage> getAll() {
		if (garageRepo.findAll().isEmpty()) {
			return null;
		} else
			return garageRepo.findAll();
	}

}
