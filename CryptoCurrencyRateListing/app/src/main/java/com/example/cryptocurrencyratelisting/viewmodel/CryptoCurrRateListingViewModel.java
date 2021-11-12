package com.example.cryptocurrencyratelisting.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cryptocurrencyratelisting.db.entity.Rates;
import com.example.cryptocurrencyratelisting.models.AppConstants;
import com.example.cryptocurrencyratelisting.models.RatesResponseModel;
import com.example.cryptocurrencyratelisting.repository.RatesRepo;
import com.example.cryptocurrencyratelisting.retrofit.ApiClient;
import com.example.cryptocurrencyratelisting.retrofit.ApiInterface;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class CryptoCurrRateListingViewModel extends AndroidViewModel {

    private RatesRepo ratesRepo;
    private LiveData<List<Rates>> allRates;

    public CryptoCurrRateListingViewModel(@NonNull Application application) {
        super(application);

//        callApi(application);
        ratesRepo = new RatesRepo(application);
        allRates = ratesRepo.getAllRates();
    }

    public void insert(Rates rate){
        ratesRepo.insert(rate);
    }

    public void update(Rates rate){
        ratesRepo.update(rate);
    }

    public LiveData<List<Rates>> getAllRates(){
        return allRates;
    }

    public void callApi(Activity activity){

        try {
            Thread ratesThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                        Call<RatesResponseModel> call = apiInterface.ratesApi(AppConstants.accessKey);
                        Response<RatesResponseModel> response = call.execute();

                        if (response.isSuccessful()) {
                            final RatesResponseModel ratesResponseModel = response.body();
                            try {
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Map<String, Double> rates = ratesResponseModel.getRates();
                                        for (Map.Entry<String, Double> entry : rates.entrySet()) {
                                            insert(new Rates(entry.getKey(), entry.getValue()));
                                        }
                                    }
                                });
                                thread.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            //show error dialogs
                            Toast.makeText(activity, "Error getting response", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            ratesThread.start();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
