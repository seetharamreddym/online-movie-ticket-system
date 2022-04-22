package com.booking.movie.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.movie.model.Movie;
import com.booking.movie.model.PatchDocument;
import com.booking.movie.service.IMovieService;


@RestController
@RequestMapping(MovieController.MOVIE)
public class MovieController {
	
	private static Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	public static final String MOVIE = "/movie";
	
	@Autowired
	IMovieService service;

	@PostMapping
	public ResponseEntity<Movie> addMovie(@Valid @RequestBody  final Movie movie) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addMovie(movie));
	}

	@GetMapping
	public List<Movie> getMovieByName(@RequestParam String name) {
		logger.info("find movies by name {}", name);
		return service.getMoviesByName(name);
	}
	
	@GetMapping("/all")
	public List<Movie> getAllMovies() {
		return service.getAllMovies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie>  getMovieById(@PathVariable Long id) {	
		return ResponseEntity.status(HttpStatus.OK).body(service.getMovieById(id));
	}
	
	
	@PatchMapping(value = MOVIE+"/{id}")
	public String updateMovie(@PathVariable Long id, @RequestBody final PatchDocument changes) {	
		return service.patchMovie(changes,id);
	}
	
	@DeleteMapping(value = MOVIE+"/{id}")
	public String deleteMovie(@PathVariable Long id) {
		 service.deleteMovie(id);
		 return "Movie deleted successfully";
	}

}
