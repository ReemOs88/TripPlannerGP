package com.example.tripplanner;

import java.io.Serializable;
import java.util.List;

public class Place implements Serializable {
    private String id;
    private String name;
    private String category;
    private String city;
    private String about;
    private String location;
    private int duration;
    private double rate;
    private List<String> images;

    public Place() {

    }

    public Place(String id, String name, String category, String city, String about, String location, int duration, double rate, List<String> images) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.city = city;
        this.about = about;
        this.location = location;
        this.duration = duration;
        this.rate = rate;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", city='" + city + '\'' +
                ", about='" + about + '\'' +
                ", location='" + location + '\'' +
                ", duration=" + duration +
                ", rate=" + rate +
                ", images=" + images +
                '}';
    }
}
