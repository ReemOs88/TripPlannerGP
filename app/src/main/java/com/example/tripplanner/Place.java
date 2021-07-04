package com.example.tripplanner;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Place implements   Parcelable {
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

    protected Place(Parcel in) {
        id = in.readString();
        name = in.readString();
        category = in.readString();
        city = in.readString();
        about = in.readString();
        location = in.readString();
        duration = in.readInt();
        rate = in.readDouble();
        images = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(city);
        dest.writeString(about);
        dest.writeString(location);
        dest.writeInt(duration);
        dest.writeDouble(rate);
        dest.writeStringList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

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
