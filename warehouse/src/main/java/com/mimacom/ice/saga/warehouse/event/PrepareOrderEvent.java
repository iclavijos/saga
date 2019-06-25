package com.mimacom.ice.saga.warehouse.event;

import java.util.Map;

public class PrepareOrderEvent {
    private Long warehouseId;
    private String orderId;
    private Map<String, Integer> prodsQuantities;

    public PrepareOrderEvent() {

    }

    public PrepareOrderEvent(Long warehouseId, String orderId, Map<String, Integer> prodsQuantities) {
        this.warehouseId = warehouseId;
        this.orderId = orderId;
        this.prodsQuantities = prodsQuantities;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<String, Integer> getProdsQuantities() {
        return prodsQuantities;
    }

    public void setProdsQuantities(Map<String, Integer> prodsQuantities) {
        this.prodsQuantities = prodsQuantities;
    }
}
