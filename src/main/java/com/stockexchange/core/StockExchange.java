package com.stockexchange.core;

import com.stockexchange.common.StockNotActiveException;
import com.stockexchange.core.impl.StockBroker;
import com.stockexchange.models.CompletedOrder;
import com.stockexchange.models.Order;
import com.stockexchange.models.OrderType;
import com.stockexchange.models.Stock;

import java.util.List;
import java.util.Map;

import static com.stockexchange.utils.FactoryUtils.createStockBrokers;

public class StockExchange {

    public Map<Stock, IStockBroker> stockBrokers;

    public StockExchange() {
        stockBrokers = createStockBrokers();
    }

    public List<CompletedOrder> placeOrder(final Order order) {
        if ( !order.getStock().isActive() )
            throw new StockNotActiveException("stock is not yet active");

        final IStockBroker stockBroker = stockBrokers.get(order.getStock());
        return OrderType.BUY == order.getOrderType() ?
            stockBroker.buy(order) : stockBroker.sell(order);
    }
}
