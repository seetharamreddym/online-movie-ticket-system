package com.booking.theatre.data;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import com.booking.theatre.data.entity.ShowEntity;

public interface ShowRepository extends CrudRepository<ShowEntity, Long>{	
	
	List<ShowEntity> findByScreenId(Long theatreId);

}
