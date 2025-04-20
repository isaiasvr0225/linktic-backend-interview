package com.microservice.inventory.presentation.controller;

import com.microservice.inventory.application.InventoryService;
import com.microservice.inventory.infrastructure.dto.InventoryAttributesDTO;
import com.microservice.inventory.infrastructure.dto.InventoryDTO;
import com.microservice.inventory.infrastructure.dto.JsonApiRequestDTO;
import com.microservice.inventory.infrastructure.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @package : com.microservice.product.presentation.controller
 * @name : InventoryController.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public @RestController class InventoryController {

    private final InventoryRepository inventoryRepository;

    private final InventoryService inventoryService;


    //@PreAuthorize("permitAll()")
    //@PreAuthorize("hasAnyRole('ADMIN', 'CARRIER')")
    @GetMapping("/{productId}")
    public ResponseEntity<InventoryDTO> findById(@PathVariable(name = "productId") UUID productId) {
        return ResponseEntity.ok(this.inventoryService.findById(productId));
    }


    //@PreAuthorize("permitAll()")
    @PostMapping
    public HttpStatus save(@RequestBody JsonApiRequestDTO<InventoryAttributesDTO> jsonApiRequestDTO) {

        return this.inventoryService.save(jsonApiRequestDTO);
    }


    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{productId}")
    public HttpStatus update(@PathVariable(name = "productId") UUID productId, @RequestBody Integer newQuantity) {
        return this.inventoryService.updateById(productId, newQuantity);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'CARRIER')")
    @DeleteMapping("/{productId}")
    public HttpStatus delete(@PathVariable(name = "productId") UUID productId) {

        return this.inventoryService.deleteById(productId);
    }


}
