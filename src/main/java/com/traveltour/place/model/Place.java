package com.traveltour.place.model;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Place {

    @Id
    public String id;

    public String name;
    //public int checkins;
    public String description;
    public List<Pair<String, String>> fields;

    public Place(String name, String description) {
        this.name = name;
        this.description = description;
        this.fields = null;
    }

    public Place(String name, String description, List<Pair<String, String>> fields) {
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    @Override
    public String toString() {
        return String.format(
                "Place[id=%s, name='%s', description='%s']",
                id, name, description);
    }
}