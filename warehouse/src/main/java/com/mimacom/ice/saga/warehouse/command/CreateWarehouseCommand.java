package com.mimacom.ice.saga.warehouse.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateWarehouseCommand {

    @TargetAggregateIdentifier
    private final Long warehouseId;
    private final String warehouseName;

    public CreateWarehouseCommand(Long id, String warehouseName) {
        this.warehouseId = id;
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }
}
