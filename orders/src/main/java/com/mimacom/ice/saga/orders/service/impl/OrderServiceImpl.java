package com.mimacom.ice.saga.orders.service.impl;

import com.mimacom.ice.saga.orders.command.CreateOrderCommand;
import com.mimacom.ice.saga.orders.command.PrepareOrderCommand;
import com.mimacom.ice.saga.orders.enums.OrderStatus;
import com.mimacom.ice.saga.orders.model.Order;
import com.mimacom.ice.saga.orders.repository.OrderRepository;
import com.mimacom.ice.saga.orders.service.OrderService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private AtomicLong ordersCounter = new AtomicLong();

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final OrderRepository repository;

    public OrderServiceImpl(CommandGateway commandGateway, QueryGateway queryGateway, OrderRepository orderRepository) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.repository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrder(String orderId) {
        return repository.getOne(orderId);
    }

    @Override
    public CompletableFuture<String> createOrder(Order order) {
        order.setId(String.format("ORD%05d", ordersCounter.incrementAndGet()));
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ACCEPTED);
        Order savedOrder = repository.save(order);
        return commandGateway.send(new CreateOrderCommand(
                savedOrder.getId(),
                savedOrder.getCustomerName(),
                savedOrder.getOrderDate(),
                savedOrder.getItems())
        );

    }

    @Override
    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public void prepareOrder(String orderId) {
        Order order = repository.getOne(orderId);
        PrepareOrderCommand command = new PrepareOrderCommand();
        command.setWarehouseId(1L);
        command.setOrderId(orderId);
        order.getItems().parallelStream().forEach(i -> command.addProductQuantity(i.getProductReference(), i.getQuantity()));
        commandGateway.send(command);
    }
}
