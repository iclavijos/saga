package com.mimacom.ice.saga.warehouse.service;

import com.mimacom.ice.saga.warehouse.aggregate.Warehouse;
import com.mimacom.ice.saga.warehouse.exception.NotEnoughStockException;
import com.mimacom.ice.saga.warehouse.model.CatalogItem;
import com.mimacom.ice.saga.warehouse.queries.GetWarehouseQuery;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface WarehouseService {

    void prepareOrder(Long warehouseId, String orderId, Map<String, Integer> products) throws NotEnoughStockException;

    Warehouse getWarehouseStatus(GetWarehouseQuery query) throws ExecutionException, InterruptedException;

    Optional<CatalogItem> getItem(String productReference);
}
