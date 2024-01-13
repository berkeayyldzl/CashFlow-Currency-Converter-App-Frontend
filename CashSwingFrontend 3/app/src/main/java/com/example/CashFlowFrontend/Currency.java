package com.example.CashFlowFrontend;

public class Currency {
//THIS IS THE MAIN CURRENCY CLASS, USED ALSO ON THE BACKEND
    private String id; // The id of the currency.
    private String mainCurrency; // The name of the currency.
    private double exchangeRate; // The numerical value of the exchange rate of the currency.
    private double max; // The maximum value of the currency in the 5 year period.
    private double min; // The minimum value of the currency in the 5 year period.

    public Currency() {
        // Default constructor for the Currency class.
    }

    public Currency(String Currency, double exchangeRate,double max, double min) {
        this.mainCurrency = Currency;
        this.exchangeRate = exchangeRate;
        this.max = max;
        this.min = min;
        // Parameterized constructor for the Currency class.
    }

    // Getters and setters of the respecting fields of the Currency class.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainCurrency() {
        return mainCurrency;
    }

    public void setMainCurrency(String mainCurrency) {
        this.mainCurrency = mainCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

}