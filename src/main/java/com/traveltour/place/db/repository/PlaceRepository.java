package com.traveltour.place.db.repository;

import com.traveltour.place.db.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaceRepository extends MongoRepository<Place, String>{

    List<Place> findByName(String name);

}
