package com.stockexchange.common;

public class StockNotActiveException extends RuntimeException {
    public StockNotActiveException( final String message ) {
        super(message);
    }
}
