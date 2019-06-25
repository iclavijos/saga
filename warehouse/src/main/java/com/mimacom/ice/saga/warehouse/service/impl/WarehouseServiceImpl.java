package com.mimacom.ice.saga.warehouse.service.impl;

import com.mimacom.ice.saga.warehouse.aggregate.Warehouse;
import com.mimacom.ice.saga.warehouse.event.StockReceivedEvent;
import com.mimacom.ice.saga.warehouse.exception.NotEnoughStockException;
import com.mimacom.ice.saga.warehouse.model.CatalogItem;
import com.mimacom.ice.saga.warehouse.model.StockItem;
import com.mimacom.ice.saga.warehouse.queries.GetWarehouseQuery;
import com.mimacom.ice.saga.warehouse.repository.CatalogItemRepository;
import com.mimacom.ice.saga.warehouse.repository.StockItemRepository;
import com.mimacom.ice.saga.warehouse.service.WarehouseService;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final StockItemRepository itemRepository;
    private final CatalogItemRepository catalogRepository;
    private final Repository<Warehouse> warehouseRepository;

    public WarehouseServiceImpl(
            StockItemRepository itemRepo,
            CatalogItemRepository catalogRepo,
            Repository<Warehouse> warehouseRepository) {
        this.itemRepository = itemRepo;
        this.catalogRepository = catalogRepo;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Transactional
    public void prepareOrder(Long warehouseId, String orderId, Map<String, Integer> products) throws NotEnoughStockException {
        products.entrySet().stream().forEach(entry -> {
           Optional<StockItem> stock = itemRepository.findByProductReference(entry.getKey());
           if (!stock.isPresent() || stock.get().getStock() < entry.getValue()) {
               throw new NotEnoughStockException("Not enough stock for product " + entry.getKey());
           } else {

           }
        });
    }

    @Override
    @QueryHandler
    @Transactional(readOnly = true)
    public Warehouse getWarehouseStatus(GetWarehouseQuery query) throws ExecutionException, InterruptedException {
        CompletableFuture<Warehouse> future = new CompletableFuture<Warehouse>();
        warehouseRepository.load("" + query.getWarehouseId()).execute(future::complete);
        return future.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CatalogItem> getItem(String productRefence) {
        return catalogRepository.findByProductReference(productRefence);
    }
}
