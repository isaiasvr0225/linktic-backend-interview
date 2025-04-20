package com.microservice.inventory.application;

import com.microservice.inventory.domain.Inventory;
import com.microservice.inventory.domain.exceptions.InventoryException;
import com.microservice.inventory.infrastructure.dto.InventoryAttributesDTO;
import com.microservice.inventory.infrastructure.dto.InventoryDTO;
import com.microservice.inventory.infrastructure.dto.JsonApiRequestDTO;
import com.microservice.inventory.infrastructure.dto.JsonApiResponseDTO;
import com.microservice.inventory.infrastructure.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public @Service class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    public HttpStatus save(JsonApiRequestDTO<InventoryAttributesDTO> jsonApiRequestDTO) {
        Inventory inventory = Inventory.builder()
                .productId(jsonApiRequestDTO.data().attributes().productId())
                .quantity(jsonApiRequestDTO.data().attributes().quantity())
                .build();

        inventoryRepository.save(inventory);
        return HttpStatus.CREATED;
    }

    @Override
    public InventoryDTO findById(UUID id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryException("Not found", HttpStatus.NOT_FOUND));

        return new InventoryDTO(
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    @Override
    public HttpStatus updateById(UUID id, Integer newQuantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryException("Not found", HttpStatus.NOT_FOUND));

        inventory.setQuantity(newQuantity);

        inventoryRepository.save(inventory);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus deleteById(UUID id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryException("Not found", HttpStatus.NOT_FOUND));

        inventoryRepository.delete(inventory);
        return HttpStatus.OK;
    }
}
