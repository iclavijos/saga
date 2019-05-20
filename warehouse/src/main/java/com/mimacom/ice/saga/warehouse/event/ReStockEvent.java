package com.mimacom.ice.saga.warehouse.event;

public class ReStockEvent {

    private String productReference;
    private Integer quantity;

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
        return "ReStockEvent{" +
                "productReference='" + productReference + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
