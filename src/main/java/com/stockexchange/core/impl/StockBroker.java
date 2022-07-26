package com.stockexchange.core.impl;

import com.stockexchange.core.IOrderInventory;
import com.stockexchange.core.IStockBroker;
import com.stockexchange.models.CompletedOrder;
import com.stockexchange.models.Order;
import com.stockexchange.models.ProcessOrderResult;
import com.stockexchange.models.OrderType;

import java.util.List;

import static com.stockexchange.utils.FactoryUtils.createProcessingOrder;

public class StockBroker implements IStockBroker {

    private final IOrderInventory sellOrderInventory;

    private final IOrderInventory buyOrderInventory;

    public StockBroker() {
        sellOrderInventory = new OrderInventory(OrderType.SELL);
        buyOrderInventory = new OrderInventory(OrderType.BUY);
    }

    /**
     *
     * @param order order
     * @return list of orders that are completed using the current SELL order
     */
    @Override
    public List<CompletedOrder> sell( final Order order ) {
        final ProcessOrderResult result = buyOrderInventory.process(createProcessingOrder(order));

        if ( null != result.getRemainingOrder() )
            sellOrderInventory.add(result.getRemainingOrder());

        return result.getCompletedOrders();
    }

    /**
     *
     * @param order order
     * @return list of orders that are completed using the current BUY order
     */
    @Override
    public List<CompletedOrder> buy( final Order order ) {
        final ProcessOrderResult result = sellOrderInventory.process(createProcessingOrder(order));

        if ( null != result.getRemainingOrder() )
            buyOrderInventory.add(result.getRemainingOrder());

        return result.getCompletedOrders();
    }
}
