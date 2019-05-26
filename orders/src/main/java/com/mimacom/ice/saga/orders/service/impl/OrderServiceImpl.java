package com.mimacom.ice.saga.orders.service.impl;

import com.mimacom.ice.saga.orders.command.CreateOrderCommand;
import com.mimacom.ice.saga.orders.model.Order;
import com.mimacom.ice.saga.orders.service.OrderService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {

    private AtomicLong ordersCounter = new AtomicLong();

    private final CommandGateway gateway;

    public OrderServiceImpl(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public CompletableFuture<String> createOrder(Order order) {

        return gateway.send(new CreateOrderCommand(
                String.format("ORD%05d", ordersCounter.incrementAndGet()),
                order.getCustomerName(),
                LocalDateTime.now(),
                order.getItems())
        );
    }
}
