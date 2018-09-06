package com.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.domain.Vehicle;
import com.example.rest.repository.VehicleRepository;
import com.example.rest.resource.VehicleResource;
import com.example.rest.resource.VehicleResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Vehicle.class)
@RequestMapping(value = "/vehicle", produces = "application/json")
public class VehicleController {
	
	@Autowired
	private VehicleRepository repository;
	
	@Autowired
	private VehicleResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<VehicleResource>> findAllvehicles() {
		List<Vehicle> vehicles = repository.findAll();
		return new ResponseEntity<>(assembler.toResourceCollection(vehicles), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<VehicleResource> createvehicle(@RequestBody Vehicle vehicle) {
		Vehicle createdvehicle = repository.create(vehicle);
		return new ResponseEntity<>(assembler.toResource(createdvehicle), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VehicleResource> findvehicleById(@PathVariable Long id) {
		Optional<Vehicle> vehicle = repository.findById(id);

		if (vehicle.isPresent()) {
			return new ResponseEntity<>(assembler.toResource(vehicle.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletevehicle(@PathVariable Long id) {
		boolean wasDeleted = repository.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<VehicleResource> updatevehicle(@PathVariable Long id, @RequestBody Vehicle updatedvehicle) {
		boolean wasUpdated = repository.update(id, updatedvehicle);
		
		if (wasUpdated) {
			return findvehicleById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
