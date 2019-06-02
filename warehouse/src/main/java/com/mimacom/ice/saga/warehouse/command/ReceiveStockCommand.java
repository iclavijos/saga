package com.mimacom.ice.saga.warehouse.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ReceiveStockCommand {

    @TargetAggregateIdentifier
    private final Long warehouseId;
    private final String productReference;
    private final Integer quantity;

    public ReceiveStockCommand(Long id, String productReference, Integer quantity) {
        this.warehouseId = id;
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public String getProductReference() {
        return productReference;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
