package com.mimacom.ice.saga.orders.aggregate;

import com.mimacom.ice.saga.orders.command.CreateOrderCommand;
import com.mimacom.ice.saga.orders.enums.OrderStatus;
import com.mimacom.ice.saga.orders.event.OrderCreatedEvent;
import com.mimacom.ice.saga.orders.model.OrderItem;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Aggregate
public class OrderAggregate {

    private static final Logger logger = LoggerFactory.getLogger(OrderAggregate.class);

    @AggregateIdentifier
    private String orderId;
    private String customerName;
    private LocalDateTime orderDate;
    private LocalDateTime preparedDate;
    private LocalDateTime shippedDate;
    private LocalDateTime deliveredDate;
    private String deliveryCompany;
    private OrderStatus status;
    private List<OrderItem> items;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        AggregateLifecycle.apply(new OrderCreatedEvent(
                command.getOrderId(),
                command.getCustomerName(),
                command.getOrderDate(),
                command.getItems()));
    }

    @EventSourcingHandler
    public void onEvent(OrderCreatedEvent event) {
        logger.info("Order created event received: {}", event);
        this.orderId = event.getOrderId();
        this.customerName = event.getCustomerName();
        this.orderDate = event.getOrderDate();
        this.items = event.getItems();
        this.status = OrderStatus.ACCEPTED;
    }

}
