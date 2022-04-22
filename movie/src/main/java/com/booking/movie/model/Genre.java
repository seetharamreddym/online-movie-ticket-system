package com.booking.movie.model;


public enum Genre {
	LOVE("Love"), CRIME("Crime"), COMEDY("comedy"),FICTION("Fiction"), DRAMA("Drama"), SCIFI("Scince Fiction"), THRILLER("Thriller");

	private String value;

	Genre(String value) {
		this.value = value;
	}

	public static Genre fromValue(String text) {
		for (Genre b : Genre.values()) {
			if (String.valueOf(b.value).equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
}
