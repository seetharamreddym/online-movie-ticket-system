package com.booking.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.theatre.data.entity.LocationEntity;
import com.booking.theatre.service.LocationService;

@RestController
@RequestMapping(LocationController.LOCATION)
public class LocationController {
	
	public static final String LOCATION = "/location";
	
	@Autowired
	private LocationService locationService;
	
	@PostMapping
	public ResponseEntity<LocationEntity> addLocation(@RequestBody LocationEntity location) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(locationService.addLocation(location));			
	}
@GetMapping
public ResponseEntity<List<LocationEntity>> getLocations(@RequestParam String filterCriteria) {	
	
		return ResponseEntity.status(HttpStatus.OK).body(locationService.getLocations(filterCriteria));			
	}


}
