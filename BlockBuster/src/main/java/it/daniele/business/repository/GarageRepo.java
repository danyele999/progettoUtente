package it.daniele.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daniele.business.Garage;

public interface GarageRepo extends JpaRepository<Garage, Long> {

}
