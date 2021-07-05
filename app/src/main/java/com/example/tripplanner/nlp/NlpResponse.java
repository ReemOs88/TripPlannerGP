package com.example.tripplanner.nlp;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class NlpResponse {

    @SerializedName("scores")
    private List<Double> scores;

    @SerializedName("confidence")
    private double confidence;

    @SerializedName("name")
    private String name;

    @SerializedName("index")
    private int index;

    public List<Double> getScores() {
        return scores;
    }

    public double getConfidence() {
        return confidence;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public boolean isPositive() {
        return name.equals("Positive");
    }
}