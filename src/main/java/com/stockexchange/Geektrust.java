package com.stockexchange;

import com.stockexchange.core.StockExchange;
import com.stockexchange.models.CompletedOrder;
import com.stockexchange.models.Order;
import com.stockexchange.models.OrderType;
import com.stockexchange.models.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Geektrust {

    static StockExchange stockBroker = new StockExchange();

    private static List<Order> readFromInputStream(String path) throws IOException {
        List<Order> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(createOrder(line));
            }
        }
        return result;
    }

    private static void printOrder( final CompletedOrder completedOrder ) {
        System.out.println(
            "#" + completedOrder.getBuyOrder().getOrderId() +
            " " + completedOrder.getSellOrder().getPrice() +
            " " + completedOrder.getUnits() +
            " #" + completedOrder.getSellOrder().getOrderId()
        );
    }

    private static Order createOrder( final String line ) {
        final String[] str = line.split("\\s+");
        Order order = new Order();
        try {
            order.setOrderId(Long.parseLong(str[0].substring(1)));
            order.setOrderTime(LocalTime.parse(str[1]));
            order.setStock(Stock.valueOf(str[2].toUpperCase()));
            order.setOrderType(OrderType.valueOf(str[3].toUpperCase()));
            order.setPrice(new BigDecimal(str[4]));
            order.setQuantity(Long.parseLong(str[5]));
        } catch ( IllegalArgumentException e ) {
            throw new RuntimeException("the input is not in the expected input format");
        }
        return order;
    }

    public static void main( String[] args ) throws IOException {
        List<Order> orders = readFromInputStream(args[0]);

        final List<List<CompletedOrder>> completedOrders = orders.stream()
            .map(stockBroker::placeOrder)
            .collect(Collectors.toList());

        completedOrders.stream()
            .filter(list -> !list.isEmpty())
            .flatMap(Collection::stream)
            .forEach(Geektrust::printOrder);
    }
}
