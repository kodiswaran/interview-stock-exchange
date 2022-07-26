package com.stockexchange.models;

import java.util.List;

public class ProcessOrderResult {
    List<CompletedOrder> completedOrders;
    ProcessingOrder remainingOrder;

    public List<CompletedOrder> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders( final List<CompletedOrder> completedOrders ) {
        this.completedOrders = completedOrders;
    }

    public ProcessingOrder getRemainingOrder() {
        return remainingOrder;
    }

    public void setRemainingOrder( final ProcessingOrder remainingOrder ) {
        this.remainingOrder = remainingOrder;
    }
}
