package com.foodpanda.shared.dto;

import com.foodpanda.shared.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Integer id;
    private List<OrderItemDto> orderItems;
    private Double totalAmount;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private LocalDateTime placedAt;
    private LocalDateTime expectedDeliveryTime;
}
