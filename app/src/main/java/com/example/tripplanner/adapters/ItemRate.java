package com.example.tripplanner.adapters;

public class ItemRate {
    private String username;
    private float rate;
    private String comment;

    public ItemRate(String username, float rate, String comment) {
        this.username = username;
        this.rate = rate;
        this.comment = comment;
    }

    public ItemRate() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
