package com.example.rest.repository;

import org.springframework.stereotype.Repository;

import com.example.rest.domain.Vehicle;

@Repository
public class VehicleRepository extends InMemoryRepository<Vehicle> {

	protected void updateIfExists(Vehicle original, Vehicle updated) {
		original.setDescription(updated.getDescription());
		original.setCostInCents(updated.getCostInCents());
		original.setComplete(updated.isComplete());
	}
}
