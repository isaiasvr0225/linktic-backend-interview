package com.microservice.inventory.infrastructure.repository;

import com.microservice.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
}