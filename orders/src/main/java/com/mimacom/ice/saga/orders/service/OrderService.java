package com.mimacom.ice.saga.orders.service;

import com.mimacom.ice.saga.orders.model.Order;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {

    List<Order> getOrders();

    Order getOrder(String orderId);

    CompletableFuture<String> createOrder(Order order);

    Order updateOrder(Order order);

    void prepareOrder(String orderId);
}
