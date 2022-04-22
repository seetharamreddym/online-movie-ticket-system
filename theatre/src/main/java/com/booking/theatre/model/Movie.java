package com.booking.theatre.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class Movie {
	
	
	private Long id;

	@NotNull
	private String name;
	
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

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

