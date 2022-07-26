package com.stockexchange.core.impl;

import com.stockexchange.core.IOrderInventory;
import com.stockexchange.models.CompletedOrder;
import com.stockexchange.models.ProcessOrderResult;
import com.stockexchange.models.OrderType;
import com.stockexchange.models.ProcessingOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static com.stockexchange.utils.CommonUtils.getComparatorsBasedOnOrderType;
import static com.stockexchange.utils.CommonUtils.isOrderPossible;
import static com.stockexchange.utils.FactoryUtils.createCompletedOrder;
import static com.stockexchange.utils.FactoryUtils.createOrderProcessResult;

public class OrderInventory implements IOrderInventory {

    OrderType orderType;
    private final TreeSet<ProcessingOrder> orders;

    OrderInventory(final OrderType orderType) {
        this.orderType = orderType;
        orders = new TreeSet<>(getComparatorsBasedOnOrderType.apply(orderType));
    }

    @Override
    public boolean add( final ProcessingOrder order ) {
        return orders.add(order);
    }

    @Override
    public synchronized ProcessOrderResult process( final ProcessingOrder processingOrder ) {
        final List<CompletedOrder> currentCompletedOrders = new ArrayList<>();
        while (isOrderProcessable(processingOrder)) {
            final ProcessingOrder bestOrder = orders.pollFirst();

            // find the order count which can be processed
            final long matchedUnits = Math.min(processingOrder.getUnitsLeft(), bestOrder.getUnitsLeft());
            processingOrder.subtractUnits(matchedUnits);
            currentCompletedOrders.add(createCompletedOrder(bestOrder, processingOrder, matchedUnits));

            // after processing the order, if the bestOrder still has few more stocks to process, add it back to the orders
            if (matchedUnits < bestOrder.getUnitsLeft()) {
                bestOrder.subtractUnits(matchedUnits);
                orders.add(bestOrder);
            }
        }

        return createOrderProcessResult(currentCompletedOrders, processingOrder);
    }

    private synchronized boolean isOrderProcessable( final ProcessingOrder order ) {
        return isOrderPossible(orderType, orders, order);
    }
}
