package com.traveltour.place.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltour.place.model.Place;
import com.traveltour.place.db.PlaceRepository;

import java.util.List;

@Service
public class PlaceServiceBean implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Autowired
    private WikipediaService wikipediaService;

    public Place getPlaceByName(String name){
        Place place = repository.getPlaceByName(name);
        if(place == null){
            return createPlace(name);
        }
        return place;
    }

    public List<Place> getAllPlaces(){
        demo();
        return repository.findAll();
    }

    public void getPlacesOfInterest(){

    }


    private void demo(){
        repository.deleteAll();

        // save a couple of places
        repository.save(new Place("Dallas", "Dallas is pretty big"));
        repository.save(new Place("Dallas, Texas", "Dallas is pretty big"));
        repository.save(new Place("New York", "The big apple"));
    }

    private Place createPlace(String name){
        Place newPlace = wikipediaService.getPlace(name);
        repository.save(newPlace);
        return newPlace;
    }
}
