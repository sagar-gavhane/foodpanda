package com.foodpanda.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    //private String restaurantId;
    private String reviewer;
    private Integer rating;

    @Column(columnDefinition = "text")
    private String review;

    private LocalDateTime createdAt = LocalDateTime.now();
}
