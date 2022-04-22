package com.booking.theatre.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.booking.theatre.data.entity.ShowEntity;
import com.booking.theatre.model.Movie;

public class TheatreUtil {
	
	// Time for movie break and other cleanup after movie completion.
	public static int BUFFER_TIME_FOR_BREAK_OTHERS=30;

	public static boolean possibleToAddShow(List<ShowEntity> shows, ShowEntity showTobeAdded, Movie movie) {

		if (!shows.isEmpty()) {
			boolean isConfliting= shows.stream()
					.anyMatch(show -> (showTobeAdded.getShowStartDateTime().isAfter(show.getShowStartDateTime())
							&& showTobeAdded.getShowStartDateTime().isBefore(show.getShowEndDateTime()))
							|| (showTobeAdded.getShowStartDateTime()
									.plusMinutes(movie.getLength() + BUFFER_TIME_FOR_BREAK_OTHERS)
									.isAfter(show.getShowStartDateTime())
									&& showTobeAdded.getShowStartDateTime()
											.plusMinutes(movie.getLength() + BUFFER_TIME_FOR_BREAK_OTHERS)
											.isBefore(show.getShowEndDateTime())));
			if(isConfliting) {
				return false;
			}
		}
		showTobeAdded.setShowEndDateTime(showTobeAdded.getShowStartDateTime().plusMinutes(movie.getLength()+BUFFER_TIME_FOR_BREAK_OTHERS));
		return true;
		
	}
	
	public static Optional<ShowEntity> getShow(List<ShowEntity> shows, Long showId) {
	return	shows.stream().filter(show-> show.getId().equals(showId)).findAny();		
	}

	
	public static boolean possibleToAddShowCopy(List<ShowEntity> shows, ShowEntity showTobeAdded, Movie movie) {

		if(!shows.isEmpty()) {
		 shows.stream().anyMatch(show -> {
			if (showTobeAdded.getShowStartDateTime().isAfter(show.getShowStartDateTime())
					&& showTobeAdded.getShowStartDateTime().isBefore(show.getShowEndDateTime())) {
				return false;
			} else {
				LocalDateTime showEndTime = showTobeAdded.getShowStartDateTime().plusMinutes(movie.getLength()+BUFFER_TIME_FOR_BREAK_OTHERS);
				if (showEndTime.isAfter(show.getShowStartDateTime())
						&& showEndTime.isBefore(show.getShowEndDateTime())) {
					return false;
				}
			}
			showTobeAdded.setShowEndDateTime(showTobeAdded.getShowStartDateTime().plusMinutes(movie.getLength()+BUFFER_TIME_FOR_BREAK_OTHERS));
			return true;
		});
		}
		showTobeAdded.setShowEndDateTime(showTobeAdded.getShowStartDateTime().plusMinutes(movie.getLength()+BUFFER_TIME_FOR_BREAK_OTHERS));
		return true;
		
	}


	public static boolean possibleToUpdateShow(List<ShowEntity> shows, ShowEntity showTobeUpdated, Movie movie) {
			boolean isConfliting= shows.stream()
					.anyMatch(show -> (showTobeUpdated.getShowStartDateTime().isAfter(show.getShowStartDateTime())
							&& showTobeUpdated.getShowStartDateTime().isBefore(show.getShowEndDateTime()))
							|| (showTobeUpdated.getShowStartDateTime()
									.plusMinutes(movie.getLength() + BUFFER_TIME_FOR_BREAK_OTHERS)
									.isAfter(show.getShowStartDateTime())
									&& showTobeUpdated.getShowStartDateTime()
											.plusMinutes(movie.getLength() + BUFFER_TIME_FOR_BREAK_OTHERS)
											.isBefore(show.getShowEndDateTime())));
			if(isConfliting) {
				return false;
			}
		return true;
		
	}
}
