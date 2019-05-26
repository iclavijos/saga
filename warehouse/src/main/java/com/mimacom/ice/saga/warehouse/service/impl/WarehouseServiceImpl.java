package com.mimacom.ice.saga.warehouse.service.impl;

import com.mimacom.ice.saga.warehouse.event.ReStockEvent;
import com.mimacom.ice.saga.warehouse.model.CatalogItem;
import com.mimacom.ice.saga.warehouse.model.StockItem;
import com.mimacom.ice.saga.warehouse.repository.CatalogItemRepository;
import com.mimacom.ice.saga.warehouse.repository.StockItemRepository;
import com.mimacom.ice.saga.warehouse.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final StockItemRepository itemRepository;
    private final CatalogItemRepository catalogRepository;

    public WarehouseServiceImpl(
            StockItemRepository itemRepo,
            CatalogItemRepository catalogRepo) {
        this.itemRepository = itemRepo;
        this.catalogRepository = catalogRepo;
    }

    @Override
    public void processNewStock(ReStockEvent newStock) {
        logger.info("Processing new stock: {}", newStock);
        Optional<CatalogItem> catalogItem = catalogRepository.findByProductReference(newStock.getProductReference());
        logger.debug("Retrieved catalog item: {} ", catalogItem);
        if (!catalogItem.isPresent()) {
            throw new RuntimeException("Invalid product reference");
        }
        StockItem item = itemRepository.findByProductReference(newStock.getProductReference()).orElse(
                new StockItem(newStock.getProductReference(), newStock.getQuantity()));
        if (item.getId() != null) {
            item.addToStock(newStock.getQuantity());
        }
        itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockItem> getWarehouseStatus() {
        return itemRepository.findAll(Sort.by(Sort.Order.asc("productReference")));
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogItem getItem(String productRefence) {
        return catalogRepository.findByProductReference(productRefence).orElseThrow(RuntimeException::new);
    }
}
