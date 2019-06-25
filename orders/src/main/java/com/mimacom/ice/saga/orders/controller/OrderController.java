package com.mimacom.ice.saga.orders.controller;

import com.mimacom.ice.saga.orders.command.CreateOrderCommand;
import com.mimacom.ice.saga.orders.command.PrepareOrderCommand;
import com.mimacom.ice.saga.orders.model.Order;
import com.mimacom.ice.saga.orders.service.OrderService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService service;

    private final AtomicInteger orderCounter = new AtomicInteger();

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @PostMapping("/")
    public CompletableFuture<String> createOrder(@RequestBody Order order) {
        logger.info("Create order received: {}", order);
        return service.createOrder(order);
    }

    @PostMapping("/{orderId}")
    public void prepareOrder(@PathVariable String orderId) {
        logger.info("Prepare order received: {}", orderId);
        service.prepareOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return service.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public void cancelOrder(@PathVariable String orderId) {

    }
}
