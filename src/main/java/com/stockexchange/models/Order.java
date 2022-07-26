package com.stockexchange.models;

import java.math.BigDecimal;
import java.time.LocalTime;
public class Order {
    long orderId;
    LocalTime orderTime;
    Stock stock;
    OrderType orderType;
    BigDecimal price;
    long quantity;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId( final long orderId ) {
        this.orderId = orderId;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime( final LocalTime orderTime ) {
        this.orderTime = orderTime;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock( final Stock stock ) {
        this.stock = stock;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType( final OrderType orderType ) {
        this.orderType = orderType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( final BigDecimal price ) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity( final long quantity ) {
        this.quantity = quantity;
    }
}
