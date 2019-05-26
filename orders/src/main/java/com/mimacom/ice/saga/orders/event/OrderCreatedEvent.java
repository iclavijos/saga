package com.mimacom.ice.saga.orders.event;

import com.mimacom.ice.saga.orders.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreatedEvent {

    private final String orderId;
    private final String customerName;
    private final LocalDateTime orderDate;
    private final List<OrderItem> items;

    public OrderCreatedEvent(String orderId, String customerName, LocalDateTime orderDate, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", items=" + items +
                '}';
    }
}
