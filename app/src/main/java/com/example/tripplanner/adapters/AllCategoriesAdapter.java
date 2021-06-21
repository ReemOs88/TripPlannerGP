package com.example.tripplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.CategoryDetailsActivity;
import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ItemAllCategoriesBinding;
import com.example.tripplanner.databinding.ItemCategoryBinding;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.Holder> {


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_all_categories, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setName("Mall " + (position + 1));

        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), "Mall " + (position + 1), Toast.LENGTH_SHORT).show();
            v.getContext().startActivity(new Intent(v.getContext(), CategoryDetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return 22;
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemAllCategoriesBinding binding;

        public Holder(@NonNull ItemAllCategoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
