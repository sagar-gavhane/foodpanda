package com.foodpanda.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Integer id;
    private Integer menuId;
    private Integer quantity;
    private Double price;
}
