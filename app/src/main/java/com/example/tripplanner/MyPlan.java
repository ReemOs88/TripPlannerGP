package com.example.tripplanner;

import java.util.ArrayList;

public class MyPlan {
    private ArrayList<Place> places = new ArrayList<>();
    private String id;
    private String name = "";

    public MyPlan() {
    }

    public MyPlan(ArrayList<Place> places, String id, String name) {
        this.places = places;
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }
}
