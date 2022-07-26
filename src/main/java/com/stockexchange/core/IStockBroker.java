package com.stockexchange.core;

import com.stockexchange.models.CompletedOrder;
import com.stockexchange.models.Order;

import java.util.List;

public interface IStockBroker {

    /**
     *
     * @param order placed order to SELL stocks
     * @return list of orders that are completed using the placed order
     */
    public List<CompletedOrder> sell( Order order );

    /**
     *
     * @param order placed order to BUY stocks
     * @return list of orders that are completed using the placed order
     */
    public List<CompletedOrder> buy( Order order );

}
