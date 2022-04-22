package com.booking.theatre.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.theatre.common.ResourceManager;
import com.booking.theatre.common.TheatreException;
import com.booking.theatre.convert.TheatreMarsheller;
import com.booking.theatre.data.ScreenRepository;
import com.booking.theatre.data.ShowRepository;
import com.booking.theatre.data.TheatreRepository;
import com.booking.theatre.data.entity.LocationEntity;
import com.booking.theatre.data.entity.ScreenEntity;
import com.booking.theatre.data.entity.SeatEntity;
import com.booking.theatre.data.entity.ShowEntity;
import com.booking.theatre.data.entity.TheatreEntity;
import com.booking.theatre.model.Movie;
import com.booking.theatre.model.PatchDocument;
import com.booking.theatre.model.Screen;
import com.booking.theatre.model.Theatre;
import com.booking.theatre.util.TheatreUtil;

@Service
public class TheatreService {

	private static Logger logger = LoggerFactory.getLogger(TheatreService.class);

	@Autowired
	TheatreRepository theatreRepo;

	@Autowired
	ScreenRepository screenRepo;

	@Autowired
	ShowRepository showRepo;

	@Autowired
	TheatreMarsheller mapper;

	@Autowired
	ResourceManager resourceManager;

	@Value("${movie.service.uri:http://localhost:8080/movie}")
	private String movieServiceUri;

	public TheatreEntity addTheatre(@Valid TheatreEntity theatre) {
		return theatreRepo.save(theatre);
	}

	public List<TheatreEntity> getTheatresByName(String name) {
		return theatreRepo.findByNameContaining(name);
	}

	public void deleteTheatre(Long id) {
		theatreRepo.deleteById(id);

	}

	public String patchTheatre(PatchDocument changes, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Theatre getTheatreScreens(Long id) {
		Optional<TheatreEntity> thetreEntity = theatreRepo.findById(id);
		if (thetreEntity.isPresent()) {
			Theatre theatre = mapper.convertToTheatrePojo(thetreEntity.get());
			theatre.getScreens().addAll(mapper.convertToScreenPojos(screenRepo.findByTheatreId(id)));
			return theatre;
		}
		return null;
	}

	public ScreenEntity addScreen(Long id, ScreenEntity screnEntity) {
		Optional<TheatreEntity> theatreEntity = theatreRepo.findById(id);
		if (theatreEntity.isPresent()) {
			screnEntity.setTheatre(theatreEntity.get());
			return screenRepo.save(screnEntity);
		}
		return null;
	}

	public Theatre getTheatre(Long id) {
		Optional<TheatreEntity> theatreEntity = theatreRepo.findById(id);
		if (theatreEntity.isPresent()) {
			return mapper.convertToTheatrePojo(theatreEntity.get());
		}
		return null;
	}

	public SeatEntity addSeatToScreen(Long theatreId, Long screenId, @Valid SeatEntity seatEntity) {
		if (!theatreRepo.findById(theatreId).isPresent()) {
			throw new TheatreException( "Invalid Theater-" + theatreId, HttpStatus.BAD_REQUEST);
		}
		List<ScreenEntity> screensInTheatre = screenRepo.findByTheatreId(theatreId);
		screensInTheatre.stream().filter(screen -> screen.getId().equals(screenId)).findAny().orElse(null);
		return null;
	}

	public ShowEntity addShow(final Long theatreId, final Long screenId, ShowEntity showEntity) {
		validateTheatre(theatreId);
		ScreenEntity screenEntity = validateAndGetScreen(theatreId, screenId);
		List<ShowEntity> shows = showRepo.findByScreenId(screenId);
		// String uri = "http://localhost:8080/movie";
		Movie movie = validateAndGetMovie(showEntity);

		boolean possibleToAddShow = TheatreUtil.possibleToAddShow(shows, showEntity, movie);
		return validateAndAddOrUpdate(showEntity, screenEntity, possibleToAddShow);
	}

	public ShowEntity updateShow(final Long theatreId, final Long screenId, final Long showId, ShowEntity showEntity) {
		validateTheatre(theatreId);
		ScreenEntity screenEntity = validateAndGetScreen(theatreId, screenId);
		List<ShowEntity> shows = showRepo.findByScreenId(screenId);
		Optional<ShowEntity> show = TheatreUtil.getShow(shows, showId);
		if (!show.isPresent()) {
			throw new TheatreException("Show not found with id-" + showId, HttpStatus.NOT_FOUND);
		}
		Movie movie = validateAndGetMovie(showEntity);
		if (showEntity.getShowEndDateTime() != null && showEntity.getShowStartDateTime()
				.plusMinutes(movie.getLength() + TheatreUtil.BUFFER_TIME_FOR_BREAK_OTHERS)
				.isAfter(showEntity.getShowEndDateTime())) {
			logger.error("Show Start time {},end time {}  is not sufficient to play movie of length(in min) {}",
					showEntity.getShowStartDateTime(), showEntity.getShowEndDateTime(), movie.getLength());
			throw new TheatreException( "Show Start,end time not sufficient to play movie",
					HttpStatus.BAD_REQUEST);
		}
		showEntity.setScreen(screenEntity);
		boolean possibleToUpdateShow = TheatreUtil.possibleToUpdateShow(shows, showEntity, movie);
		return validateAndAddOrUpdate(showEntity, screenEntity, possibleToUpdateShow);

	}

	private ShowEntity validateAndAddOrUpdate(ShowEntity showEntity, ScreenEntity screenEntity,
			boolean possibleToAddOrUpdateShow) {
		if (!possibleToAddOrUpdateShow) {
			throw new TheatreException("Conflicting show timing with other shows",
					HttpStatus.BAD_REQUEST);
		} else {
			showEntity.setScreen(screenEntity);
			return showRepo.save(showEntity);
		}
	}

	private ScreenEntity validateAndGetScreen(final Long theatreId, final Long screenId) {
		ScreenEntity screenEntity = screenRepo.findByTheatreId(theatreId).stream()
				.filter(screen -> screen.getId().equals(screenId)).findAny().orElse(null);
		if (screenEntity == null) {
			throw new TheatreException("Screen not found with given id- " + screenId,
					HttpStatus.NOT_FOUND);
		}
		return screenEntity;
	}

	private void validateTheatre(final Long theatreId) {
		if (!theatreRepo.findById(theatreId).isPresent()) {
			throw new TheatreException( "Invalid Theater - " + theatreId, HttpStatus.NOT_FOUND);
		}
	}

	private Movie validateAndGetMovie(ShowEntity showEntity) {
		RestTemplate restTemplate = new RestTemplate();
		String fullMovieUri = movieServiceUri + "/" + showEntity.getMovieid();
		logger.info("movie uri::{}" , fullMovieUri);
		Movie movie = restTemplate.getForObject(fullMovieUri, Movie.class);
		if (movie == null) {
			throw new TheatreException( "movie not found with given id-" + showEntity.getMovieid(),
					HttpStatus.NOT_FOUND);
		}
		return movie;
	}

	public void deleteShow(Long theatreId, Long screenId, Long showId) {
		validateTheatre(theatreId);
		validateAndGetScreen(theatreId, screenId);
		List<ShowEntity> shows = showRepo.findByScreenId(screenId);
		Optional<ShowEntity> show = TheatreUtil.getShow(shows, showId);
		if (!show.isPresent()) {
			throw new TheatreException( "Show not found with id-" + showId, HttpStatus.NOT_FOUND);
		}
		showRepo.deleteById(showId);

	}

	public List<Theatre> getTheatresAndShows(List<LocationEntity> locations, Long movieId, LocalDate date) {

		List<Theatre> result = new ArrayList<>();

		locations.forEach(location -> {
			List<TheatreEntity> theatres = theatreRepo.findByPinCode(location.getPinCode());
			theatres.forEach(theatre -> {					
				List<ScreenEntity> screens = screenRepo.findByTheatreId(theatre.getId());
				screens.forEach(screen -> {
					List<ShowEntity> shows = showRepo.findByScreenId(screen.getId());
					shows.forEach(show-> {
						if(show.getMovieid().equals(movieId) && show.getShowStartDateTime().toLocalDate().equals(date)) {
						Theatre theatrePojo = mapper.convertToTheatrePojo(theatre);	
						result.add(theatrePojo);
						Screen screenPojo = mapper.convertToScreenPojo(screen);
						theatrePojo.getScreens().add(screenPojo);
						screenPojo.getShows().add(mapper.convertToShowPojo(show));
						}
					});
				});
			});
		});

		return result;
	}

}
