package com.traveltour.place.service;

import com.traveltour.place.model.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getPlacesByName(String name);

    List<Place> getAllPlaces();

}
