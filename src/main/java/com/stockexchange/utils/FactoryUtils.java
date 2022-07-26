package com.stockexchange.utils;

import com.stockexchange.core.IStockBroker;
import com.stockexchange.core.impl.StockBroker;
import com.stockexchange.models.CompletedOrder;
import com.stockexchange.models.Order;
import com.stockexchange.models.ProcessOrderResult;
import com.stockexchange.models.OrderType;
import com.stockexchange.models.ProcessingOrder;
import com.stockexchange.models.Stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactoryUtils {

    /**
     * Using the {@link Stock} Enum list, create the active stockbrokers
     * @return list of stock broker
     */
    public static Map<Stock, IStockBroker> createStockBrokers() {
        final Map<Stock, IStockBroker> stockBrokers = new HashMap<>();
        for ( Stock stock: Stock.values() )
            if ( stock.isActive() )
                stockBrokers.put(stock, new StockBroker());
        return stockBrokers;
    }

    /**
     * @param processingOrder the processing order
     * @param matchedOrder matched order
     * @param units number of units to be processed
     * @return completed order object
     */
    public static CompletedOrder createCompletedOrder( final ProcessingOrder processingOrder, ProcessingOrder matchedOrder, final long units ) {
        CompletedOrder completedOrder = new CompletedOrder();
        completedOrder.setUnits(units);
        if ( OrderType.BUY == matchedOrder.getOrder().getOrderType() ) {
            completedOrder.setBuyOrder(matchedOrder.getOrder());
            completedOrder.setSellOrder(processingOrder.getOrder());
        } else {
            completedOrder.setBuyOrder(processingOrder.getOrder());
            completedOrder.setSellOrder(matchedOrder.getOrder());
        }
        return completedOrder;
    }

    /**
     * * @param order order
     * @return create an ProcessingOrder class
     */
    public static ProcessingOrder createProcessingOrder( final Order order ) {
        ProcessingOrder processingOrder = new ProcessingOrder();
        processingOrder.setOrder(order);
        processingOrder.setUnitsLeft(order.getQuantity());
        return processingOrder;
    }

    /**
     *
     * @param completedOrders completed orders
     * @param processingOrder processingOrder
     * @return create OrderProcessResult class
     */
    public static ProcessOrderResult createOrderProcessResult( List<CompletedOrder> completedOrders,
                                                               ProcessingOrder processingOrder) {
        final ProcessOrderResult processOrderResult = new ProcessOrderResult();
        processOrderResult.setCompletedOrders(completedOrders);
        if ( processingOrder.getUnitsLeft() > 0 )
            processOrderResult.setRemainingOrder(processingOrder);
        return processOrderResult;
    }
}
