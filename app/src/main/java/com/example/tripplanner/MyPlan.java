package com.example.tripplanner;

import java.util.ArrayList;

public class MyPlan {
    private ArrayList<Place> places = new ArrayList<>();
    private String id;

    public MyPlan() {
    }

    public MyPlan(ArrayList<Place> places, String id) {
        this.places = places;
        this.id = id;
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
