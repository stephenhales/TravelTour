package com.traveltour.place.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Place {

    @Id
    public String id;

    public String name;
    public int checkins;
    public String description;
    public List<String> fields;

    public Place(String name, int checkins) {
        this.name = name;
        this.checkins = checkins;
    }

    public Place(String name, String description, List<String> fields) {
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    @Override
    public String toString() {
        return String.format(
                "Place[id=%s, name='%s', checkins='%s']",
                id, name, checkins);
    }
}