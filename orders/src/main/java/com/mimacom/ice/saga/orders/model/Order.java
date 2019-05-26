package com.mimacom.ice.saga.orders.model;

import com.mimacom.ice.saga.orders.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String id;
    private String customerName;
    private LocalDateTime orderDate;
    private LocalDateTime preparedDate;
    private LocalDateTime shippedDate;
    private LocalDateTime deliveredDate;
    private String deliveryCompany;
    private OrderStatus status;
    private List<OrderItem> items;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.ACCEPTED;
    }

    public Order(String customerName, List<OrderItem> items) {
        this.customerName = customerName;
        this.orderDate = LocalDateTime.now();
        this.items = items;
        this.status = OrderStatus.ACCEPTED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", preparedDate=" + preparedDate +
                ", shippedDate=" + shippedDate +
                ", deliveredDate=" + deliveredDate +
                ", deliveryCompany='" + deliveryCompany + '\'' +
                ", status=" + status +
                ", items=" + items +
                '}';
    }

    public LocalDateTime getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(LocalDateTime preparedDate) {
        this.preparedDate = preparedDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
