package com.booking.theatre.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.booking.theatre.data.entity.TheatreEntity;

public interface TheatreRepository  extends CrudRepository<TheatreEntity, Long>{	

	List<TheatreEntity> findByNameContaining(String name);

	List<TheatreEntity> findByPinCode(int pinCode);
}
