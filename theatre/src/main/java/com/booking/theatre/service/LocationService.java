package com.booking.theatre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.theatre.data.LocationRepository;
import com.booking.theatre.data.entity.LocationEntity;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

	public LocationEntity addLocation(LocationEntity location) {
	return	locationRepository.save(location);
	}

	public List<LocationEntity> getLocations(String filterCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocationEntity> getLocationsInCity(String cityName) {
		return locationRepository.findByCityNameLike(cityName);
		
	}

}
