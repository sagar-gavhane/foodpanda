package com.foodpanda.order.controller;

import com.foodpanda.order.service.OrderService;
import com.foodpanda.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto) {
        OrderDto placedOrder = orderService.placeOrder(orderDto);
        return ResponseEntity.ok(placedOrder);
    }
}
