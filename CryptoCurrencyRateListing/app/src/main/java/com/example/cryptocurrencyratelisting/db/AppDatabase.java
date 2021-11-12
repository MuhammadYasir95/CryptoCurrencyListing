package com.example.cryptocurrencyratelisting.db;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cryptocurrencyratelisting.db.dao.RateDao;
import com.example.cryptocurrencyratelisting.db.entity.Rates;

@Database(entities = {Rates.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RateDao rateDao();

    @VisibleForTesting
    public static final String DATABASE_NAME = "crypto-db";

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
//                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            // new PopulateDb(instance).execute();
//        }
//    }
}
