package com.booking.theatre.data;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import com.booking.theatre.data.entity.LocationEntity;

public interface LocationRepository extends CrudRepository<LocationEntity, Long>{	
	
	List<LocationEntity> findByPinCode(int pinCode);
	List<LocationEntity> findByStateCode(String stateCode);
	List<LocationEntity> findByCityNameLike(String cityName);	
}
