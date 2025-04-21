package com.microservice.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "products")
public @Entity class Product {
    @Id
    @Column(name = "id")
    private UUID id;
    private String name;
    private BigDecimal price;
}

