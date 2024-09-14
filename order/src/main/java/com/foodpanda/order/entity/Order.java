package com.foodpanda.order.entity;

import com.foodpanda.shared.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private Double totalAmount;
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime placedAt;
    private LocalDateTime expectedDeliveryTime;
}
