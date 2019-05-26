package com.mimacom.ice.saga.orders.model;

public class OrderItem {

    private String productReference;
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderItem{" +
                "productReference='" + productReference + '\'' +
                ", quantity=" + quantity +
                '}';
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

    public OrderItem(String productReference, Integer quantity) {
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public OrderItem() {
    }
}
