package com.example.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.example.rest.domain.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleResource extends ResourceSupport {

	private final long id;
	private final String description;
	private final long costInCents;
	private final boolean isComplete;
	
	public VehicleResource(Vehicle vehicle) {
		id = vehicle.getId();
		description = vehicle.getDescription();
		costInCents = vehicle.getCostInCents();
		isComplete = vehicle.isComplete();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public long getCostInCents() {
		return costInCents;
	}

	public boolean isComplete() {
		return isComplete;
	}
}
