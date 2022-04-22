package com.booking.theatre.data.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ScreenEntity {
	
	public ScreenEntity() {
	}

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String supportedFormat;
	private String soundSystem;
	private String screenSize;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "theatre_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TheatreEntity theatre;

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

	public TheatreEntity getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreEntity theatre) {
		this.theatre = theatre;
	}

}
