package com.booking.theatre.model;

import java.util.ArrayList;
import java.util.List;

public class Screen {

	private Long id;

	private String name;
	private String supportedFormat;
	private String soundSystem;
	private String screenSize;
	private List<Show> shows =  new ArrayList<>();
	
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

	public String getSupportedFormat() {
		return supportedFormat;
	}

	public void setSupportedFormat(String supportedFormat) {
		this.supportedFormat = supportedFormat;
	}

	public String getSoundSystem() {
		return soundSystem;
	}

	public void setSoundSystem(String soundSystem) {
		this.soundSystem = soundSystem;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

}
