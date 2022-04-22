package com.booking.movie.service;

import java.util.List;

import com.booking.movie.model.Movie;
import com.booking.movie.model.PatchDocument;

public interface IMovieService {

	Movie getMovieByName();

	Movie addMovie(Movie movie);

	List<Movie> getMoviesByName(String name);

	void deleteMovie(Long id);

	String patchMovie(PatchDocument changes, Long id);

	Movie getMovieById(Long id);

	List<Movie> getAllMovies();

}