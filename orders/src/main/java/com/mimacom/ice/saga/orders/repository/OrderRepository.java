package com.mimacom.ice.saga.orders.repository;

import com.mimacom.ice.saga.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
