package com.booking.movie.covert;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.booking.movie.common.MovieException;
import com.booking.movie.common.ResourceManager;
import com.booking.movie.data.entity.MovieDetails;
import com.booking.movie.model.Genre;
import com.booking.movie.model.Movie;
import com.booking.movie.model.PatchDocument;
import com.booking.movie.service.MovieConstants;

@Component
public class MovieMarshaller {
	
	private static Logger logger = LoggerFactory.getLogger(MovieMarshaller.class);	

	@Autowired
	ResourceManager resourceMgr;

	public Movie generateMovieModel(MovieDetails movieEntity) {
		Movie movie = new Movie();
		movie.setId(movieEntity.getId());
		movie.setCast(movieEntity.getCasting());
		movie.setName(movieEntity.getName());
		movie.setGenre(movieEntity.getGenre());
		movie.setLength(movieEntity.getLength());
		movie.setLanguage(movieEntity.getLanguage());
		movie.setFormat(movieEntity.getFormat());
		movie.setReleaseDate(movieEntity.getReleaseDate());
		movie.setCertification(movieEntity.getCertification());
		return movie;
	}

	public MovieDetails generateMovieEntity(Movie movie) {
		MovieDetails movieEntity = new MovieDetails();
		movieEntity.setCasting(movie.getCast());
		movieEntity.setName(movie.getName());		
		movieEntity.setGenre(movie.getGenre());
		movieEntity.setLength(movie.getLength());
		movieEntity.setLanguage(movie.getLanguage());
		movieEntity.setFormat(movie.getFormat());
		movieEntity.setReleaseDate(movie.getReleaseDate());
		movieEntity.setCertification(movie.getCertification());
		return movieEntity;
	}

	public List<Movie> readMovies(List<MovieDetails> movieEntities) {
		List<Movie> movies = new ArrayList<>();
		movieEntities.forEach(movieEntity -> {
			movies.add(generateMovieModel(movieEntity));
		});
		return movies;
	}

	public void changeMovieDetails(PatchDocument changes, MovieDetails movieDetails) {
		switch (changes.getOp()) {
		case ADD:
		case REPLACE:
			updateValue(changes.getPath(), changes.getValue().toString(), movieDetails);
		default:
			logger.warn("Unsuported patch operation");
			break;
		}
	}

	private void updateValue(final String field, final String newValue, MovieDetails movieDetails) {

		switch (field) {
		case MovieConstants.NAME:
			movieDetails.setName(newValue);
			break;
		case MovieConstants.CAST:
			movieDetails.setCasting(newValue);
			break;
		case MovieConstants.GENRE:
			if(Genre.fromValue(newValue) == null ) {
				logger.info("Invalid data for  {} value is   {} ",field, newValue);
				throw new MovieException(resourceMgr, "movie.input.error","Unsupport Genre :" + newValue, HttpStatus.BAD_REQUEST);
			}		
			movieDetails.setGenre(newValue);
			break;
		case MovieConstants.LENGTH:
			movieDetails.setLength(Integer.parseInt(newValue));
			break;
		case MovieConstants.LANGUAGE:
			movieDetails.setLanguage(newValue);
			break;
		case MovieConstants.FORMAT:
			movieDetails.setFormat(newValue);
			break;
		default:
			logger.info("field  {} not present in the movie ", field);
			break;
		}
		}

	public List<Movie> readMovies(Iterable<MovieDetails> allMovies) {
		List<Movie> movies = new ArrayList<>();
		allMovies.forEach(movieEntity -> {
			movies.add(generateMovieModel(movieEntity));
		});
		return movies;
	}

}
