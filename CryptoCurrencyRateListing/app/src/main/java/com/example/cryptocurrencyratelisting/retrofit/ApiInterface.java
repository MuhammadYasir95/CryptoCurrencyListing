package com.example.cryptocurrencyratelisting.retrofit;


import com.example.cryptocurrencyratelisting.models.AppConstants;
import com.example.cryptocurrencyratelisting.models.RatesResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(AppConstants.URL_RATES)
    Call<RatesResponseModel> ratesApi(@Query("access_key") String access_key);
}
