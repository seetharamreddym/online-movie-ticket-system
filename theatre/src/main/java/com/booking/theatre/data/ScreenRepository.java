package com.booking.theatre.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.booking.theatre.data.entity.ScreenEntity;

public interface ScreenRepository extends CrudRepository<ScreenEntity, Long>{	
	
	List<ScreenEntity> findByTheatreId(Long theatreId);
	
	  @Transactional
	  void deleteByTheatreId(Long theatreId);
}
