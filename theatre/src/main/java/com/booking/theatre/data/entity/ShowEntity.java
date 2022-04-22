package com.booking.theatre.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ShowEntity {

	public ShowEntity() {
	}

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@NotNull
	private LocalDateTime showStartDateTime;

	private LocalDateTime showEndDateTime;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "screen_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ScreenEntity screen;
	
	@NotNull
	private Long movieid;

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

	public ScreenEntity getScreen() {
		return screen;
	}

	public void setScreen(ScreenEntity screen) {
		this.screen = screen;
	}

	public Long getMovieid() {
		return movieid;
	}

	public void setMovieid(Long movieid) {
		this.movieid = movieid;
	}

}
