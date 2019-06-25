package com.mimacom.ice.saga.orders.model;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String productReference;
    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(String productReference, Integer quantity) {
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
