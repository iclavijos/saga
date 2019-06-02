package com.mimacom.ice.saga.warehouse.aggregate;

import com.mimacom.ice.saga.warehouse.command.CreateWarehouseCommand;
import com.mimacom.ice.saga.warehouse.command.ReceiveStockCommand;
import com.mimacom.ice.saga.warehouse.event.StockReceivedEvent;
import com.mimacom.ice.saga.warehouse.event.WarehouseCreatedEvent;
import com.mimacom.ice.saga.warehouse.service.WarehouseService;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.queryhandling.*;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Aggregate
public class Warehouse {

    @Autowired
    private WarehouseService service;

    @AggregateIdentifier
    private Long warehouseId;
    private String name;
    private Map<String, Integer> stocks; //<Product_reference, Quantity>

    public Warehouse() {

    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    @Bean
    public SimpleQueryBus queryBus(AxonConfiguration axonConfiguration, TransactionManager transactionManager) {
        return SimpleQueryBus.builder()
                .messageMonitor(axonConfiguration.messageMonitor(QueryBus.class, "queryBus"))
                .transactionManager(transactionManager)
                .errorHandler(axonConfiguration.getComponent(
                        QueryInvocationErrorHandler.class,
                        () -> LoggingQueryInvocationErrorHandler.builder().build()
                ))
                .queryUpdateEmitter(axonConfiguration.getComponent(QueryUpdateEmitter.class))
                .build();
    }

    @CommandHandler
    public Warehouse(CreateWarehouseCommand command) {
        Assert.notNull(command.getWarehouseId(), "Warehouse id cannot be null");
        Assert.notNull(command.getWarehouseName(), "Warehouse name cannot be null");

        AggregateLifecycle.apply(new WarehouseCreatedEvent(command.getWarehouseId(), command.getWarehouseName()));
    }

    @CommandHandler
    public void receiveStock(ReceiveStockCommand command) {
        Assert.notNull(command.getWarehouseId(), "Warehouse id cannot be null");

        Assert.notNull(command.getProductReference(), "Product reference cannot be null");
        Assert.isTrue(service.getItem(command.getProductReference()).isPresent(), "Product reference not valid");

        Assert.notNull(command.getQuantity(), "Quantity cannot be null");
        Assert.isTrue(command.getQuantity() > 0, "Quantity must be greater than zero");

        AggregateLifecycle.apply(
                new StockReceivedEvent(command.getWarehouseId(), command.getProductReference(), command.getQuantity()));
    }

    @EventSourcingHandler
    private void handleWarehouseCreatedEvent(WarehouseCreatedEvent event) {
        this.warehouseId = event.getId();
        this.name = event.getName();
        this.stocks = new HashMap<>();
    }

    @EventSourcingHandler
    public void stockReceived(StockReceivedEvent event) {
        Integer stock = Optional.ofNullable(stocks.get(event.getProductReference())).orElse(0);
        stock += event.getQuantity();
        stocks.put(event.getProductReference(), stock);
    }
}
