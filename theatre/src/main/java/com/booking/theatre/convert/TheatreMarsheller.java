package com.booking.theatre.convert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.booking.theatre.data.entity.ScreenEntity;
import com.booking.theatre.data.entity.ShowEntity;
import com.booking.theatre.data.entity.TheatreEntity;
import com.booking.theatre.model.Screen;
import com.booking.theatre.model.Show;
import com.booking.theatre.model.Theatre;


@Component
public class TheatreMarsheller {

	public Theatre convertToTheatrePojo(TheatreEntity theatreEntity) {
		Theatre theatre = new Theatre();
		theatre.setId(theatreEntity.getId());
		theatre.setName(theatreEntity.getName());
		theatre.setCity(theatreEntity.getCity());
		theatre.setLocation(theatreEntity.getLocation());
		theatre.setPinCode(theatreEntity.getPinCode());
		return theatre;
	}
	
	public Screen convertToScreenPojo(ScreenEntity screenEntity) {
		Screen screen = new Screen();
		screen.setId(screenEntity.getId());
		screen.setName(screenEntity.getName());
		screen.setScreenSize(screenEntity.getScreenSize());
		screen.setSupportedFormat(screenEntity.getSupportedFormat());
		screen.setSoundSystem(screenEntity.getSoundSystem());
		return screen;
	}

	public List<Screen> convertToScreenPojos(final List<ScreenEntity> screenEntities) {		
		List<Screen> screens = new ArrayList<>();		
		screenEntities.forEach(screenEntity-> {
			screens.add(convertToScreenPojo(screenEntity));
		});
		return screens;
	}

	public List<Show> convertToShowPojoList(List<ShowEntity> showEntities) {
		List<Show> shows = new ArrayList<>();
//		ObjectMapper mapper= new ObjectMapper();
//		List<Show> shows = mapper.readValue(showEntities, new TypeReference<List<Show>>(){});
		showEntities.forEach(showEntity-> {
			shows.add(convertToShowPojo(showEntity));
		});
		return shows;
		
		
	}

	public Show convertToShowPojo(ShowEntity showEntity) {
		Show show = new Show();
		show.setId(showEntity.getId());
		show.setName(showEntity.getName());
		show.setShowStartDateTime(showEntity.getShowStartDateTime());
		show.setShowEndDateTime(showEntity.getShowEndDateTime());
		show.setMovieId(showEntity.getMovieid());
		return show;
	} 
}
