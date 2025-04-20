package com.microservice.inventory.application;


import com.microservice.inventory.infrastructure.dto.InventoryAttributesDTO;
import com.microservice.inventory.infrastructure.dto.InventoryDTO;
import com.microservice.inventory.infrastructure.dto.JsonApiRequestDTO;
import com.microservice.inventory.infrastructure.dto.JsonApiResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * @package : com.microservice.inventory.application
 * @name : InventoryService.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

public interface InventoryService {

    /**
     * This method is used to save a new inventory
     * @param inventoryDTO InventoryDTO
     * @return HttpStatus
     */
    HttpStatus save(JsonApiRequestDTO<InventoryAttributesDTO> inventoryDTO);

    /**
     * This method is used to find a inventory by id
     * @param id id
     * @return InventoryDTO
     */
    InventoryDTO findById(UUID id);

    /**
     * This method is used to update a inventory by id
     * @param id id
     * @param newQuantity Integer
     * @return HttpStatus
     */
    HttpStatus updateById(UUID id, Integer newQuantity);

    /**
     * This method is used to delete a product by id
     * @param id id
     * @return HttpStatus
     */
    HttpStatus deleteById(UUID id);
}
