package com.mimacom.ice.saga.warehouse.queries;

public class GetWarehouseQuery {

    private final Long warehouseId;

    public GetWarehouseQuery(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() {
        return this.warehouseId;
    }
}
