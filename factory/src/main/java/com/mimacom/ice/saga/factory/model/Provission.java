package com.mimacom.ice.saga.factory.model;

public class Provission {
    private String productReference;
    private Integer quantity;

    public Provission(String productReference, Integer quantity) {
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Provission{" +
                "productReference='" + productReference + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
