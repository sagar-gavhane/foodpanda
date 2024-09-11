package com.foodpanda.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //private String restaurantId
    private String category;
    private String name;
    private Float price;

    @Column(columnDefinition = "text")
    private String description;

    private boolean available = false;
}
