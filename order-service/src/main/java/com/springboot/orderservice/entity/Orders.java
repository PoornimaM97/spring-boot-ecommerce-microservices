package com.springboot.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime createdAt;


}
