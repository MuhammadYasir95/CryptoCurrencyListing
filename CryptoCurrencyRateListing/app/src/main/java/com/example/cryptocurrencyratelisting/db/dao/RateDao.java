package com.example.cryptocurrencyratelisting.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cryptocurrencyratelisting.db.entity.Rates;

import java.util.List;

@Dao
public interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Rates rate);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void InsertAll(Rates... rates);

    @Update
    void Update(Rates rate);

//    @Update
//    void UpdateAll(Rates... rates);

    @Query("Select * from rates")
    LiveData<List<Rates>> getAllRates();

    @Query("SELECT * FROM rates WHERE symbols = :symbols")
    int isDataExist(String symbols);
}
