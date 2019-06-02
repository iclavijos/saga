package com.mimacom.ice.saga.warehouse.event;

import org.axonframework.modelling.command.AggregateIdentifier;

public class StockReceivedEvent {

    @AggregateIdentifier
    private Long warehouseId;
    private String productReference;
    private Integer quantity;

    public StockReceivedEvent() {

    }

    public StockReceivedEvent(Long warehouseId, String productReference, Integer quantity) {
        this.warehouseId = warehouseId;
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockReceivedEvent{" +
                "productReference='" + productReference + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
