package com.foodpanda.order.repository;

import com.foodpanda.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
