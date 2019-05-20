package com.mimacom.ice.saga.warehouse.service;

import com.mimacom.ice.saga.warehouse.event.ReStockEvent;
import com.mimacom.ice.saga.warehouse.model.CatalogItem;
import com.mimacom.ice.saga.warehouse.model.StockItem;

import java.util.List;

public interface WarehouseService {

    void processNewStock(ReStockEvent newStock);

    List<StockItem> getWarehouseStatus();

    CatalogItem getItem(String productReference);
}
