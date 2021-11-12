package com.example.cryptocurrencyratelisting.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.cryptocurrencyratelisting.db.AppDatabase;
import com.example.cryptocurrencyratelisting.db.dao.RateDao;
import com.example.cryptocurrencyratelisting.db.entity.Rates;

import java.util.List;

public class RatesRepo {

    private RateDao rateDao;
    private LiveData<List<Rates>> allRates;

    public RatesRepo(Application application){
        AppDatabase database = AppDatabase.getInstance(application.getApplicationContext());
        rateDao = database.rateDao();
        allRates = rateDao.getAllRates();
    }

    public void insert(Rates rate){
        new InsertRatesAsyncTask(rateDao).execute(rate);
    }

    public void update(Rates rate){
        new UpdateRatesAsyncTask(rateDao).execute(rate);
    }

    public LiveData<List<Rates>> getAllRates(){
        return allRates;
    }

    private static class InsertRatesAsyncTask extends AsyncTask<Rates,Void,Void>{
        private RateDao rateDao;
        private InsertRatesAsyncTask(RateDao rateDao){
            this.rateDao = rateDao;
        }

        @Override
        protected Void doInBackground(Rates... rates) {
            if(rateDao.isDataExist(rates[0].symbols) == 0) {
                rateDao.Insert(rates[0]);
            }else {
                rateDao.Update(rates[0]);
            }
            return null;
        }

    }

    private static class UpdateRatesAsyncTask extends AsyncTask<Rates,Void,Void>{
        private RateDao rateDao;
        private UpdateRatesAsyncTask(RateDao rateDao){
            this.rateDao = rateDao;
        }

        @Override
        protected Void doInBackground(Rates... rates) {
            rateDao.Update(rates[0]);
            return null;
        }

    }
}
