package com.example.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.example.rest.domain.Vehicle;

@Component
public class VehicleResourceAssembler extends ResourceAssembler<Vehicle, VehicleResource> {
	
	@Autowired
	protected EntityLinks entityLinks;

	private static final String UPDATE_REL = "update";
	private static final String DELETE_REL = "delete";

	@Override
	public VehicleResource toResource(Vehicle vehicle) {
		
		VehicleResource resource = new VehicleResource(vehicle);
		
		final Link selfLink = entityLinks.linkToSingleResource(vehicle);
		
		resource.add(selfLink.withSelfRel());
		resource.add(selfLink.withRel(UPDATE_REL));
		resource.add(selfLink.withRel(DELETE_REL));
		
		return resource;
	}
}
