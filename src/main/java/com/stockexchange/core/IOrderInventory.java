package com.stockexchange.core;

import com.stockexchange.models.ProcessOrderResult;
import com.stockexchange.models.ProcessingOrder;


public interface IOrderInventory {

    /**
     *
     * @param processingOrder the processing order detail
     * @return true if the order is added to the inventory
     */
    public boolean add( ProcessingOrder processingOrder );

    /**
     *
     * @param processingOrder  the processing order detail
     * @return the OrderProcessResult after processing the order
     */
    public ProcessOrderResult process( ProcessingOrder processingOrder );

}
