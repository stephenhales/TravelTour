package com.traveltour.place.service;

import com.traveltour.place.model.Place;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class WikipediaService {
    public Place getPlace(String name){
        StringBuffer content = extractData(name);
        return transformData(content);
    }

    private StringBuffer extractData(String name) {
        String wikipedia = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&rvprop=content&titles=";
        URL url = null;

        try {
            url = new URL(wikipedia + name);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        StringBuffer content = new StringBuffer();
        try {
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private Place transformData(StringBuffer content){
        Place newPlace = new Place(
                "London",
                "It's in England",
                getFields(content));
        return newPlace;
    }

    private List<String> getFields(StringBuffer content){
        List<String> fields = new ArrayList<>();
        fields.add(getHistory(content));
        return fields;
    }

    private String getHistory(StringBuffer content){
        String start = ">Recreation<";
        String end = ">Sport<";
        return content.substring(content.indexOf(start), content.indexOf(end));
    }
}
