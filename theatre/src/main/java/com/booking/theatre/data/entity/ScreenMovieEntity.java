package com.booking.theatre.data.entity;

import javax.persistence.Entity;

public class ScreenMovieEntity {

	public ScreenMovieEntity() {
	}

	private Long screenId;
	private Long movieId;

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
