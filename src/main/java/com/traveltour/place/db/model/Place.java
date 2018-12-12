package com.traveltour.place.db.model;


import com.traveltour.common.coordinate.Coordinate;
import org.springframework.data.annotation.Id;

public class Place {

    @Id
    public String id;
    public String name;
    public int checkins;
    public String url;
    public Coordinate centerCoordinate;
    public int distance;
    public String category;

    public Place() {}
}