package com.bijanghanei.orderservice.repository;

import com.bijanghanei.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
