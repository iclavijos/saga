package com.mimacom.ice.saga.warehouse.model;

import javax.persistence.*;

@Entity
public class CatalogItem {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private String productReference;
    @Column private String productName;
    @Column private String description;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
