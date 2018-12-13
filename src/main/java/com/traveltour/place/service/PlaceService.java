package com.traveltour.place.service;

import com.traveltour.place.db.model.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getPlacesByName(String name);

}
