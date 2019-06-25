package com.mimacom.ice.saga.orders.event;

public class OrderDeliveredEvent {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDeliveredEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
