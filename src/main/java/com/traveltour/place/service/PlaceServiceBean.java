package com.traveltour.place.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltour.place.model.Place;
import com.traveltour.place.db.PlaceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        return Arrays.asList();
    }

    private void demo(){
        repository.deleteAll();

        List<String> places = Arrays.asList("London",
                "Paris",
                "Barcelona",
                "Angkor Wat",
                "Great Barrier Reef",
                "Machu Picchu",
                "Great Wall of China");

        for(String place : places){
            getPlaceByName(place);
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch(InterruptedException e){

            }
        }


    }

    private Place createPlace(String name){
        Place newPlace = new Place(name, "", wikipediaService.getPlaceDetails(name));
        repository.save(newPlace);
        return newPlace;
    }
}
