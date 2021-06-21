package com.example.tripplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ItemReviewBinding;

import java.util.List;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.RatesHolder> {
    private final List<ItemRate> rates;

    public RatesAdapter(List<ItemRate> rates) {
        this.rates = rates;
    }

    @NonNull
    @Override
    public RatesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatesHolder(DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesHolder holder, int position) {
        holder.binding.setData(rates.get(position));
    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    class RatesHolder extends RecyclerView.ViewHolder {
        ItemReviewBinding binding;

        public RatesHolder(@NonNull ItemReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}