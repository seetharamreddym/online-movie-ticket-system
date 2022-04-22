package com.booking.theatre.model;

import java.time.LocalDateTime;

public class Show {

	private Long id;

	private String name;
	
	private Long movieId;

	private LocalDateTime showStartDateTime;

	private LocalDateTime showEndDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getShowStartDateTime() {
		return showStartDateTime;
	}

	public void setShowStartDateTime(LocalDateTime showStartDateTime) {
		this.showStartDateTime = showStartDateTime;
	}

	public LocalDateTime getShowEndDateTime() {
		return showEndDateTime;
	}

	public void setShowEndDateTime(LocalDateTime showEndDateTime) {
		this.showEndDateTime = showEndDateTime;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
