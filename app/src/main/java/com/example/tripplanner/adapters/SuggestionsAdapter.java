package com.example.tripplanner.adapters;

import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tripplanner.CategoryDetailsActivity;
import com.example.tripplanner.Place;
import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ItemAllCategoriesBinding;
import com.example.tripplanner.databinding.ItemSuggestionBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.Holder> {
    private final ArrayList<Place> places;

    public SuggestionsAdapter(ArrayList<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_suggestion, parent, false)
        );
    }

    private static final String TAG = "SuggestionsAdapter";

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Place place = places.get(position);

        Glide.with(holder.itemView.getContext()).load(place.getImages().get(0)).into(holder.binding.image);

        if (position == places.size() - 1) {
            holder.binding.spaceLayout.setVisibility(View.GONE);
            holder.binding.durationLayout.setVisibility(View.GONE);
        } else {
            holder.binding.spaceLayout.setVisibility(View.VISIBLE);
            holder.binding.durationLayout.setVisibility(View.VISIBLE);

            double distance = getDistance(place, places.get(position + 1));

            Log.i(TAG, "onBindViewHolder: " + places.get(position).getLocation() + " ! " + places.get(position + 1).getLocation());
            Log.i(TAG, "onBindViewHolder: old " + distance);

            String[] latLng1 = places.get(position).getLocation().split(",");
            String[] latLng2 = places.get(position + 1).getLocation().split(",");

            Log.i(TAG, "onBindViewHolder: new " + distance(
                    Double.parseDouble(latLng1[0]),
                    Double.parseDouble(latLng1[1]),
                    Double.parseDouble(latLng2[0]),
                    Double.parseDouble(latLng2[1])
            ));

            double duration;
            if (distance > 1) {
                duration = (distance / 60 * 60 + 10); // 80 Km/h

            } else {
                duration = (distance / 60 * 60 + 5); // 80 Km/h
            }

            holder.binding.tvDistance.setText(new DecimalFormat("###.#").format(distance) + " Km");

            holder.binding.tvDuration.setText(new DecimalFormat("###").format(duration) + " m");
        }

        holder.binding.setName(place.getName());

        holder.itemView.setOnClickListener(v -> {
            v.getContext().startActivity(new Intent(v.getContext(), CategoryDetailsActivity.class)
                    .putExtra("place", place));
        });
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private double getDistance(Place place1, Place place2) {
        Location location1 = new Location(place1.getName());
        String[] latLng1 = place1.getLocation().split(",");
        location1.setLatitude(Double.parseDouble(latLng1[0]));
        location1.setLatitude(Double.parseDouble(latLng1[1]));

        Location location2 = new Location(place2.getName());
        String[] latLng2 = place2.getLocation().split(",");
        location2.setLatitude(Double.parseDouble(latLng2[0]));
        location2.setLatitude(Double.parseDouble(latLng2[1]));

        return location1.distanceTo(location2) / 1000;
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemSuggestionBinding binding;

        public Holder(@NonNull ItemSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void analysisReview() {


    }
}
