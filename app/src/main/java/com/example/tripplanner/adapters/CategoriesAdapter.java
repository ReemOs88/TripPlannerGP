package com.example.tripplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tripplanner.CategoryDetailsActivity;
import com.example.tripplanner.Place;
import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ItemCategoryBinding;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    private final List<Place> places;

    public CategoriesAdapter(List<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_category, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Place place = places.get(position);

        holder.binding.setName(place.getName());

        Glide.with(holder.itemView.getContext()).load(place.getImages().get(0)).into(holder.binding.placeImage);

        holder.itemView.setOnClickListener(v -> {
            v.getContext().startActivity(new Intent(v.getContext(), CategoryDetailsActivity.class)
                    .putExtra("place", place));
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public Holder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
