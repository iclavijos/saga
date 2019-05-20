package com.mimacom.ice.saga.warehouse.model;

import javax.persistence.*;

@Entity
public class StockItem {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column private String productReference;
    @Column private Integer stock;

    public StockItem() {
        super();
    }

    public StockItem(String productRefence, Integer stock) {
        this.productReference = productRefence;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void addToStock(Integer quantity) {
        this.stock = this.stock + quantity;
    }
}
