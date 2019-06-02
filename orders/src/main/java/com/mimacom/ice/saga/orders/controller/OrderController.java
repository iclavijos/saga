package com.mimacom.ice.saga.orders.controller;

import com.mimacom.ice.saga.orders.model.Order;
import com.mimacom.ice.saga.orders.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController("/orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public CompletableFuture<String> createOrder(@RequestBody Order order) {
        logger.info("Create order received: {}", order);
        return orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public void cancelOrder(@PathVariable String orderId) {

    }
}
