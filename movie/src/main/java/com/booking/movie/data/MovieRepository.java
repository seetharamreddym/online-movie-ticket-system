package com.booking.movie.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.movie.data.entity.MovieDetails;

@Repository
public interface MovieRepository  extends CrudRepository<MovieDetails, Long>{	
	
  List<MovieDetails>	findByNameContainingIgnoreCase(final String name) ;
	
}
