package com.stockexchange.models;

import java.math.BigDecimal;

public class ProcessingOrder {
    Order order;
    long unitsLeft;

    public Order getOrder() {
        return order;
    }

    public void setOrder( final Order order ) {
        this.order = order;
    }

    public long getUnitsLeft() {
        return unitsLeft;
    }

    public void setUnitsLeft( final long unitsLeft ) {
        this.unitsLeft = unitsLeft;
    }

    public BigDecimal getPrice() {
        return this.getOrder().getPrice();
    }

    public void subtractUnits(long count) {
        unitsLeft -= count;
    }
}
