package com.mimacom.ice.saga.orders.saga;

import com.mimacom.ice.saga.orders.command.PrepareOrderCommand;
import com.mimacom.ice.saga.orders.event.NotEnoughStockEvent;
import com.mimacom.ice.saga.orders.event.OrderCreatedEvent;
import com.mimacom.ice.saga.orders.event.OrderDeliveredEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Saga
public class OrderManagementSaga {
    private final Logger logger = LoggerFactory.getLogger(OrderManagementSaga.class);

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void orderCreated(OrderCreatedEvent event) {
        logger.info("Saga started for order {}", event.getOrderId());

        //Do whatever business requirements specify
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void orderDelivered(OrderDeliveredEvent event) {
        logger.info("Saga ended for order {}", event.getOrderId());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void notEnoughStock(NotEnoughStockEvent event) {
        logger.info("Order {} cancelled because not enough stock to serve it", event.getOrderId());
    }
}
