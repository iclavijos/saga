package com.mimacom.ice.saga.orders.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PrepareOrderCommand {
    private Long warehouseId;
    @TargetAggregateIdentifier
    private String orderId;
    private Map<String, Integer> prodsQuantities;
    private LocalDateTime orderDate;

    public PrepareOrderCommand() {
        this.prodsQuantities = new HashMap<>();
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

    public void addProductQuantity(String productReference, Integer productQuantity) {
        Integer quantity = Optional.ofNullable(prodsQuantities.get(productReference)).orElse(0);
        prodsQuantities.put(productReference, quantity + productQuantity);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
