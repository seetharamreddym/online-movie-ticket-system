package com.booking.theatre.controller;

import org.springframework.web.bind.annotation.RestController;

import com.booking.theatre.data.entity.LocationEntity;
import com.booking.theatre.data.entity.ScreenEntity;
import com.booking.theatre.data.entity.SeatEntity;
import com.booking.theatre.data.entity.ShowEntity;
import com.booking.theatre.data.entity.TheatreEntity;
import com.booking.theatre.model.PatchDocument;
import com.booking.theatre.model.Theatre;
import com.booking.theatre.service.LocationService;
import com.booking.theatre.service.TheatreService;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(TheatreController.THEATRE)
public class TheatreController {

	private static final String SHOW = "/show";

	private static final String SEAT = "/seat";

	private static final String SCREEN_ID = "/{screenId}";
	

	private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);

	public static final String THEATRE = "/theatre";
	public static final String SCREEN = "/screen";
	private static final String ID = "/{theatreId}";

	@Autowired
	private TheatreService service;

	@PostMapping
	public ResponseEntity<TheatreEntity> addTheatre(@Valid @RequestBody final TheatreEntity theatre) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addTheatre(theatre));
	}

	@GetMapping
	public ResponseEntity<List<TheatreEntity>> getTheatreByName(@RequestParam String name) {
		logger.info("find Theatres by name {}", name);
		return ResponseEntity.status(HttpStatus.OK).body(service.getTheatresByName(name));
	}

	@PatchMapping(ID)
	public String updateTheatre(@PathVariable Long theatreId, @RequestBody final PatchDocument changes) {
		return service.patchTheatre(changes, theatreId);
	}

	@GetMapping(ID + SCREEN)
	public ResponseEntity<Theatre> getTheatreScreens(@PathVariable Long theatreId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getTheatreScreens(theatreId));
	}

	@PostMapping(ID + SCREEN)
	public ResponseEntity<ScreenEntity> addScreenToTheatre(@PathVariable Long theatreId,
			@Valid @RequestBody final ScreenEntity screnEntity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addScreen(theatreId, screnEntity));
	}

	@PostMapping(ID + SCREEN + SCREEN_ID + SEAT)
	public ResponseEntity<SeatEntity> addSeatsToTheatreScreen(@PathVariable Long theatreId, @PathVariable Long screenId,
			@Valid @RequestBody final SeatEntity seatEntity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addSeatToScreen(theatreId, screenId, seatEntity));
	}

	@PostMapping(ID + SCREEN + SCREEN_ID + SHOW)
	public ResponseEntity<ShowEntity> addShow(@PathVariable Long theatreId, @PathVariable Long screenId,
			@Valid @RequestBody final ShowEntity showEntity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addShow(theatreId, screenId, showEntity));
	}

	@PutMapping(ID + SCREEN + SCREEN_ID + SHOW + "/{showId")
	public ResponseEntity<ShowEntity> updateShow(@PathVariable Long theatreId, @PathVariable Long screenId,
			@PathVariable Long showId, @Valid @RequestBody final ShowEntity showEntity) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateShow(theatreId, screenId, showId, showEntity));
	}

	@DeleteMapping(ID)
	public String deleteTheatre(@PathVariable Long id) {
		service.deleteTheatre(id);
		return "Theatre deleted successfully";
	}

	@GetMapping(ID)
	public ResponseEntity<Theatre> getTheatre(@PathVariable Long theatreId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getTheatre(theatreId));
	}

	@DeleteMapping(ID + SCREEN + SCREEN_ID + SHOW + "/{showId")
	public String deleteShow(@PathVariable Long theatreId, @PathVariable Long screenId, @PathVariable Long showId) {
		service.deleteShow(theatreId, screenId, showId);
		return "show deleted successfully";
	}

}
