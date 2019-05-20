package com.mimacom.ice.saga.warehouse.controller;

import com.mimacom.ice.saga.warehouse.dto.ItemDTO;
import com.mimacom.ice.saga.warehouse.service.WarehouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService service) {
        this.warehouseService = service;
    }

    @GetMapping
    public List<ItemDTO> getWarehouseStatus() {
        return warehouseService.getWarehouseStatus().stream().map(i -> {
            ItemDTO item = new ItemDTO();
            item.setProductName(warehouseService.getItem(i.getProductReference()).getProductName());
            item.setProductReference(i.getProductReference());
            item.setStock(i.getStock());
            return item;
        }).collect(Collectors.toList());
    }
}
