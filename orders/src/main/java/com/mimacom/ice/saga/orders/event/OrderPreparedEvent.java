package com.mimacom.ice.saga.orders.event;

public class OrderPreparedEvent {

    private String orderId;

    public OrderPreparedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
