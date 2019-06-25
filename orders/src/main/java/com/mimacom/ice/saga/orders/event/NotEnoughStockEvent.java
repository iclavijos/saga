package com.mimacom.ice.saga.orders.event;

public class NotEnoughStockEvent {

    private String orderId;

    public NotEnoughStockEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
