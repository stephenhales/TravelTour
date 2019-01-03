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
        return repository.findAll();
    }

    public void getPlacesOfInterest(){

    }


    private void demo(){
        repository.deleteAll();

        // save a couple of places
        repository.save(new Place("Dallas", 200));
        repository.save(new Place("Dallas, Texas", 3500));
        repository.save(new Place("New York", 10000));

        // fetch all places
        System.out.println("Place found with findAll():");
        System.out.println("-------------------------------");
        for (Place place : repository.findAll()) {
            System.out.println(place);
        }
        System.out.println();

        // fetch an individual Place
        System.out.println("Place found with getPlaceByName('Dallas'):");
        System.out.println("--------------------------------");
        System.out.println(repository.getPlaceByName("Dallas"));
        System.out.println();

        System.out.println("Places found with getPlacesByName('Dallas, Texas'):");
        System.out.println("--------------------------------");
        for (Place place : repository.getPlacesByName("Dallas, Texas")) {
            System.out.println(place);
        }
        System.out.println();
    }

    private Place createPlace(String name){
        Place newPlace = wikipediaService.getPlace(name);
        //repository.save(newPlace);
        return newPlace;
    }
}
