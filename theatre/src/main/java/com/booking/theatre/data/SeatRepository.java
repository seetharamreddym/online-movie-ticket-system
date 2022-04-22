package com.booking.theatre.data;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.booking.theatre.data.entity.SeatEntity;

public interface SeatRepository extends CrudRepository<SeatEntity, Long>{	
	
	List<SeatEntity> findByRowNumber(Integer rowNumber);	
	List<SeatEntity> findByScreenId(Long screenId);	
}
