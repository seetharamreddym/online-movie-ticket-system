package com.booking.movie.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class Movie {
	
	
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private String cast;
	
	/*
	 * Action, thriller, comedy, horror, scifi
	 */
	@NotNull
	private String genre;
	
	/*
	 * 2D, 3D
	 */
	@NotNull
	private String format;
	
	/*
	 * Length of the movie in minutes
	 */
	@Min(5)
	private int length;
	
	@NotNull
	private String language;

	/*
	 * (U), (U/A), (A) and (S).
	 */
	@NotNull
	private String certification;
	
	private LocalDateTime releaseDate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
}

