package com.example.cryptocurrencyratelisting.models;

import java.util.List;
import java.util.Map;

public class RatesResponseModel {

    private String target;
    private Map<String, Double> rates;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
