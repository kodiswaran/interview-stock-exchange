package com.stockexchange.utils;

import com.stockexchange.models.OrderType;
import com.stockexchange.models.ProcessingOrder;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class CommonUtils {

    /**
     * Comparator to order the {@link ProcessingOrder} in ascending order based on orderId
     */
    final private static Comparator<ProcessingOrder> comparatorOrderIdAscending =
        Comparator.comparingLong(order -> order.getOrder().getOrderId());

    /**
     * Comparator to order the {@link ProcessingOrder} in ascending order based on price
     */
    final private static Comparator<ProcessingOrder> comparatorPriceAscending =
        Comparator.comparing(ProcessingOrder::getPrice)
            .thenComparing(comparatorOrderIdAscending);

    /**
     * Comparator to order the {@link ProcessingOrder} in descending order based on price
     */
    final private static Comparator<ProcessingOrder> comparatorPriceDescending =
        Comparator.comparing(ProcessingOrder::getPrice)
            .reversed()
            .thenComparing(comparatorOrderIdAscending);

    /**
     * Get the comparator based on the order type,
     * - {@link OrderType#BUY}  - sort the orders in descending order based on price, orderId
     * - {@link OrderType#SELL} - sort the orders in ascending order based on price, orderId
     */
    final public static Function<OrderType, Comparator<ProcessingOrder>> getComparatorsBasedOnOrderType = orderType ->
        OrderType.SELL == orderType ? comparatorPriceAscending : comparatorPriceDescending;

    /**
     * Check if the SELL order is possible
     */
    private static final BiPredicate<TreeSet<ProcessingOrder>,  ProcessingOrder> isSellPossible = (buyOrders, processingOrder)
        -> !buyOrders.isEmpty() &&
        processingOrder.getUnitsLeft() > 0 &&
        processingOrder.getPrice().doubleValue() <= buyOrders.first().getPrice().doubleValue();

    /**
     * Check if the BUY order is possible
     */
    private static final BiPredicate<TreeSet<ProcessingOrder>, ProcessingOrder> isBuyPossible = (sellOrders, processingOrder)
        -> !sellOrders.isEmpty() &&
        processingOrder.getUnitsLeft() > 0 &&
        processingOrder.getPrice().doubleValue() >= sellOrders.first().getPrice().doubleValue();

    /**
     * Check if the order is processable
     */
    public static boolean isOrderPossible(OrderType orderType, TreeSet<ProcessingOrder> orders, ProcessingOrder processingOrder) {
        return OrderType.SELL == orderType ?
            isBuyPossible.test(orders, processingOrder):
            isSellPossible.test(orders, processingOrder);
    }
}
