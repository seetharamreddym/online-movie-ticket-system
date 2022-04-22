package com.booking.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.booking.movie.common.MovieException;
import com.booking.movie.common.ResourceManager;
import com.booking.movie.covert.MovieMarshaller;
import com.booking.movie.data.MovieRepository;
import com.booking.movie.data.entity.MovieDetails;
import com.booking.movie.model.Genre;
import com.booking.movie.model.Movie;
import com.booking.movie.model.PatchDocument;

@Service
public class MovieService implements IMovieService {

	@Autowired
	MovieRepository repo;

	@Autowired
	MovieMarshaller converter;

	@Autowired
	ResourceManager resourceMgr;

	@Override
	public Movie getMovieByName() {

		return new Movie();
	}

	@Override
	public Movie addMovie(Movie movie) {
		if(Genre.fromValue(movie.getGenre()) == null ) {
			throw new MovieException(resourceMgr, "Unsupport Genre :" + movie.getGenre(), HttpStatus.BAD_REQUEST);
		}		
		return converter.generateMovieModel(repo.save(converter.generateMovieEntity(movie)));
	}

	@Override
	public List<Movie> getMoviesByName(final String name) {
		return converter.readMovies(repo.findByNameContainingIgnoreCase(name));
	}

	@Override
	public void deleteMovie(final Long id) {
		Optional<MovieDetails> movie = repo.findById(id);
		if (movie.isPresent()) {
		repo.deleteById(id);
		}

		throw new MovieException(resourceMgr, "movie not found with id : " + id, HttpStatus.NOT_FOUND);

	}

	@Override
	public String patchMovie(PatchDocument changes, final Long id) {		
		Optional<MovieDetails> movie = repo.findById(id);
		if (movie.isPresent()) {
			MovieDetails movieDetails = movie.get();
			converter.changeMovieDetails(changes, movieDetails);
			repo.save(movieDetails);
			return "updated successfully";
		}

		throw new MovieException(resourceMgr, "movie not found with id : " + id, HttpStatus.NOT_FOUND);

	}

	@Override
	public Movie getMovieById(Long id) {
		Optional<MovieDetails> movie = repo.findById(id);
		if (movie.isPresent()) {
			MovieDetails movieDetails = movie.get();
		return	converter.generateMovieModel(movieDetails);
		}
		return null;
	}

	@Override
	public List<Movie> getAllMovies() {
		return converter.readMovies(repo.findAll());
	}

}
