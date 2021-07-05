package com.example.tripplanner;

import java.util.ArrayList;

public class MyPlan {
    private ArrayList<Place> places = new ArrayList<>();

    public MyPlan() {
    }

    public MyPlan(ArrayList<Place> places) {
        this.places = places;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }
}
