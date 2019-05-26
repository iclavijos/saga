package com.mimacom.ice.saga.orders.command;

import com.mimacom.ice.saga.orders.model.OrderItem;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String customerName;
    private final LocalDateTime orderDate;
    private final List<OrderItem> items;

    public CreateOrderCommand(String orderId, String customerName, LocalDateTime orderDate, List<OrderItem> items) {
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
}
