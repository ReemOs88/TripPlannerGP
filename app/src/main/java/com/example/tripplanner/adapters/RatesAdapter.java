package com.example.tripplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ItemReviewBinding;
import com.example.tripplanner.nlp.ApiClient;
import com.example.tripplanner.nlp.NlpRequest;
import com.example.tripplanner.nlp.NlpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ItemRate itemRate = rates.get(position);

        holder.binding.setData(itemRate);

        if (itemRate.isPositive()){
            holder.binding.nlpImage.setImageResource(R.drawable.ic_like);
        }
        else {
            holder.binding.nlpImage.setImageResource(R.drawable.ic_dis_like);
        }
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