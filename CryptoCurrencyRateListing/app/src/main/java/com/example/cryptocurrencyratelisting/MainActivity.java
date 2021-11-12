package com.example.cryptocurrencyratelisting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.cryptocurrencyratelisting.adapter.CryptoRatesListingAdapter;
import com.example.cryptocurrencyratelisting.databinding.ActivityMainBinding;
import com.example.cryptocurrencyratelisting.db.entity.Rates;
import com.example.cryptocurrencyratelisting.viewmodel.CryptoCurrRateListingViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        CryptoCurrRateListingViewModel cryptoCurrRateListingViewModel = new ViewModelProvider(this).get(
                CryptoCurrRateListingViewModel.class);

        cryptoCurrRateListingViewModel.callApi(this);


        cryptoCurrRateListingViewModel.getAllRates().observe(this,rates -> {
            binding.rvCryptoRates.setLayoutManager(new LinearLayoutManager(this));
            binding.rvCryptoRates.setHasFixedSize(true);
            binding.tvCount.setText(""+rates.size());
            binding.rvCryptoRates.setAdapter(new CryptoRatesListingAdapter((List<Rates>) rates));
        });

    }
}