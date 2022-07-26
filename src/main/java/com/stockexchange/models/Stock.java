package com.stockexchange.models;

public enum Stock {

    BAC("Bank of America Corp", true),
    AMZN("Amazon.com", true),
    FRSH("Freshworks Inc", true);

    private final String stockName;

    private final boolean active;

    Stock( final String stockName, final boolean active ) {
        this.stockName = stockName;
        this.active = active;
    }

    public String getStockName() {
        return stockName;
    }

    public boolean isActive() {
        return active;
    }
}
