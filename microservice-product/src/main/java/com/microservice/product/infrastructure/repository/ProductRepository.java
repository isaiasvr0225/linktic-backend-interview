package com.microservice.product.infrastructure.repository;

import com.microservice.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}