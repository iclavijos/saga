package com.mimacom.ice.saga.orders.service;

import com.mimacom.ice.saga.orders.model.Order;

import java.util.concurrent.CompletableFuture;

public interface OrderService {

    CompletableFuture<String> createOrder(Order order);
}
