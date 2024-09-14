package com.foodpanda.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Integer id;
    private String category;
    private String name;
    private Float price;
    private String description;
    private boolean available = false;
}
