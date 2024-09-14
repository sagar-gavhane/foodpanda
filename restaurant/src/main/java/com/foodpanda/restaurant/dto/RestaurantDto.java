package com.foodpanda.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String cuisineType;
    private String operatingHours;
    private String city;
    private Float latitude;
    private Float longitude;
}
