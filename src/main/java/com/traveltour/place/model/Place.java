package com.traveltour.place.model;

import org.springframework.data.annotation.Id;

public class Place {

    @Id
    public String id;

    public String name;
    public int checkins;

    //public String url;
    //public Coordinate centerCoordinate;
    //public int distance;
    //public String category;

    public Place(String name, int checkins) {
        this.name = name;
        this.checkins = checkins;
    }

    @Override
    public String toString() {
        return String.format(
                "Place[id=%s, name='%s', checkins='%s']",
                id, name, checkins);
    }
}