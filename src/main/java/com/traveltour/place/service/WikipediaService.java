package com.traveltour.place.service;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WikipediaService {
    public List<Pair<String, String>> getPlaceDetails(String name){
        int initialHeaderLevel = 2;
        return getFields(extractData(name).toString(), initialHeaderLevel);
    }

    private Document extractData(String name) {
        String wikipedia = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&rvprop=content&titles=";
        Document doc = null;
        try {
            doc = Jsoup.connect(wikipedia + name).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    private List<Pair<String, String>> getFields(String content, int headerLevel){
        List<Pair<String, String>> fields = new ArrayList<>();

        String[] sections = getSections(content, headerLevel);
        for (String section : sections){
            Pair<String, String> field = getField(splitSection(section, headerLevel));
            fields.add(field);
            if(field.getRight().contains("<h" + headerLevel+1 +">")){
                fields.addAll(getFields(field.getRight(), headerLevel+1));
            }
        }
        return fields;
    }

    private String[] getSections(String content, int headerLevel){
        return content.split("<h"+headerLevel+">");
    }

    private String[] splitSection(String section, int headerLevel){
        return section.split("</h"+headerLevel+">");
    }

    private Pair<String, String> getField(String[] section){
        if(section.length == 2){
            return Pair.of(getTitle(section[0]), section[1]);
        }
        //TODO
        return null;
    }

    private String getTitle(String section){
        String start = ">";
        String end = "</span>";
        return section.substring(section.indexOf(start)+1, section.indexOf(end));
    }
}
