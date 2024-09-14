package com.foodpanda.shared.event;

import com.foodpanda.shared.dto.OrderDto;
import com.foodpanda.shared.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEvent {
    private Integer orderId;
    private OrderStatus orderStatus;
    private OrderDto orderDto;
}
