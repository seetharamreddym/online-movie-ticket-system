package com.booking.theatre.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.theatre.data.entity.LocationEntity;
import com.booking.theatre.model.Theatre;
import com.booking.theatre.service.LocationService;
import com.booking.theatre.service.TheatreService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private static final String MOVIE_ID = "/{movieId}";

	@Autowired
	private LocationService locationService;

	@Autowired
	private TheatreService service;

	@GetMapping(MOVIE_ID)
	public ResponseEntity<List<Theatre>> getTheatresAndShows(@PathVariable Long movieId, @RequestParam String cityName,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<LocationEntity> locations = locationService.getLocationsInCity(cityName);
		return ResponseEntity.status(HttpStatus.OK).body(service.getTheatresAndShows(locations, movieId, date));
	}
}
