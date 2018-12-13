package com.traveltour.place.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltour.place.db.model.Place;
import com.traveltour.place.db.repository.PlaceRepository;

import java.util.List;

@Service
public class PlaceServiceBean implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getPlacesByName(String name){
        return placeRepository.findByName(name);
    }
}
