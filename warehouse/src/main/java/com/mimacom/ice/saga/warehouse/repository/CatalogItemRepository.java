package com.mimacom.ice.saga.warehouse.repository;

import com.mimacom.ice.saga.warehouse.model.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {

    Optional<CatalogItem> findByProductReference(String reference);
}
