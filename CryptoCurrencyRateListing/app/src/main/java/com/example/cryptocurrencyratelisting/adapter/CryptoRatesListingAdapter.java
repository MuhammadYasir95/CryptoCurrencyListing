package com.example.cryptocurrencyratelisting.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencyratelisting.databinding.CryptoRateListingViewBinding;
import com.example.cryptocurrencyratelisting.db.entity.Rates;

import java.util.List;

public class CryptoRatesListingAdapter extends RecyclerView.Adapter<CryptoRatesListingAdapter.ViewHolderCryptoRates>{

    private final List<Rates> ratesList;

    public CryptoRatesListingAdapter(List<Rates> ratesList) {
        this.ratesList = ratesList;
    }

    @NonNull
    @Override
    public ViewHolderCryptoRates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CryptoRateListingViewBinding binding = CryptoRateListingViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolderCryptoRates(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCryptoRates holder, int position) {
        Rates rates = ratesList.get(position);
        holder.cryptoRateListingViewBinding.tvSymbol.setText(rates.symbols);
        holder.cryptoRateListingViewBinding.tvRate.setText(String.valueOf(rates.cryptoRates));
    }

    @Override
    public int getItemCount() {
        return ratesList.size();
    }

    public class ViewHolderCryptoRates extends RecyclerView.ViewHolder{

        final  CryptoRateListingViewBinding cryptoRateListingViewBinding;
        public ViewHolderCryptoRates(@NonNull CryptoRateListingViewBinding binding) {
            super(binding.getRoot());

            cryptoRateListingViewBinding = binding;
        }
    }
}
