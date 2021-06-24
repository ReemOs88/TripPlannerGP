package com.example.tripplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.CategoryDetailsActivity;
import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ItemAllCategoriesBinding;
import com.example.tripplanner.databinding.ItemSuggestionBinding;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.Holder> {

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_suggestion, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (position == 9){
            holder.binding.spaceLayout.setVisibility(View.GONE);
            holder.binding.durationLayout.setVisibility(View.GONE);
        }
        else {
            holder.binding.spaceLayout.setVisibility(View.VISIBLE);
            holder.binding.durationLayout.setVisibility(View.VISIBLE);
        }

        holder.binding.setName("Mall " + (position + 1));

        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), "Mall " + (position + 1), Toast.LENGTH_SHORT).show();
            v.getContext().startActivity(new Intent(v.getContext(), CategoryDetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemSuggestionBinding binding;

        public Holder(@NonNull ItemSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
