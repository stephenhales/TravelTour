package com.traveltour.place.db.repository;

import com.traveltour.place.db.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place, String>{

    Place findByName(String name);

}
