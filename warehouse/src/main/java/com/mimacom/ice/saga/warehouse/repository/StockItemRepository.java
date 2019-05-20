package com.mimacom.ice.saga.warehouse.repository;

import com.mimacom.ice.saga.warehouse.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    Optional<StockItem> findByProductReference(String productReference);
}
