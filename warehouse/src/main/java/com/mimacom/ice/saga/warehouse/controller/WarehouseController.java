package com.mimacom.ice.saga.warehouse.controller;

import com.mimacom.ice.saga.warehouse.aggregate.Warehouse;
import com.mimacom.ice.saga.warehouse.queries.GetWarehouseQuery;
import com.mimacom.ice.saga.warehouse.service.WarehouseService;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController("/warehouse")
public class WarehouseController {

    private final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseService warehouseService;
    private final QueryGateway queryGateway;

    public WarehouseController(WarehouseService service, QueryGateway queryGateway) {
        this.warehouseService = service;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseStatus(@PathVariable Long id) throws InterruptedException, ExecutionException {
        logger.info("Received request for warehouse -{}- status", id);
        CompletableFuture<Warehouse> future = queryGateway.query(new GetWarehouseQuery(id), Warehouse.class);
        return future.get();
    }
}
