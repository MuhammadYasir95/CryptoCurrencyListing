package com.example.cryptocurrencyratelisting.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rates")
public class Rates {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "symbols")
    public String symbols;

    @ColumnInfo(name = "crypto_rates")
    public double cryptoRates;

    public Rates() {
    }

    @Ignore
    public Rates(String symbols, double cryptoRates) {
        this.symbols = symbols;
        this.cryptoRates = cryptoRates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public double getCryptoRates() {
        return cryptoRates;
    }

    public void setCryptoRates(double cryptoRates) {
        this.cryptoRates = cryptoRates;
    }
}
