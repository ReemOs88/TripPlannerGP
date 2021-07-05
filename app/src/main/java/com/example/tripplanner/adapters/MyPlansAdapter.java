package com.example.tripplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.MyPlan;
import com.example.tripplanner.R;
import com.example.tripplanner.SuggestionsActivity;
import com.example.tripplanner.databinding.ItemMyPlansBinding;

import java.util.List;

public class MyPlansAdapter extends RecyclerView.Adapter<MyPlansAdapter.MyPlansHolder> {
    private final List<MyPlan> myPlans;

    public MyPlansAdapter(List<MyPlan> myPlans) {
        this.myPlans = myPlans;
    }

    @NonNull
    @Override
    public MyPlansHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyPlansHolder(DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_my_plans, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlansHolder holder, int position) {
        holder.binding.setPlanName("Plan " + (position + 1));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SuggestionsActivity.class);
            intent.putExtra("finalPlaces", myPlans.get(position).getPlaces());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myPlans.size();
    }

    class MyPlansHolder extends RecyclerView.ViewHolder {
        ItemMyPlansBinding binding;

        public MyPlansHolder(@NonNull ItemMyPlansBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}