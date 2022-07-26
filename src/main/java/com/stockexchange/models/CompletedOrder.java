package com.stockexchange.models;

public class CompletedOrder {
    Order buyOrder;
    Order sellOrder;
    long units;

    public Order getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder( final Order buyOrder ) {
        this.buyOrder = buyOrder;
    }

    public Order getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder( final Order sellOrder ) {
        this.sellOrder = sellOrder;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits( final long units ) {
        this.units = units;
    }
}
