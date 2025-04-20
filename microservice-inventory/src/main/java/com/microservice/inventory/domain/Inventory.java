package com.microservice.inventory.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "inventories")
@Entity
public class Inventory {
    @Id
    @Column(name = "product_id")
    private UUID productId;
    private Integer quantity;
}

