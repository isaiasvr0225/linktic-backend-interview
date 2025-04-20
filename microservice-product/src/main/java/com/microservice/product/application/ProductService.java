package com.microservice.product.application;

import com.microservice.product.infrastructure.dto.JsonApiResponseDTO;
import com.microservice.product.infrastructure.dto.ProductResponseDTO;
import com.microservice.product.infrastructure.dto.SaveNewProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * @package : com.microservice.product.application
 * @name : ProductService.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

public interface ProductService {

    /**
     * This method is used to find all products using pagination
     * @param pageable pageable
     * @return Page<ProductResponseDTO>
     */
    Page<ProductResponseDTO> findAll(Pageable pageable);

    /**
     * This method is used to save a product
     * @param saveNewProductDTO SaveNewProductDTO
     * @return HttpStatus
     */
    HttpStatus save(SaveNewProductDTO saveNewProductDTO);

    /**
     * This method is used to find a product by id
     * @param id id
     * @return ProductResponseDTO
     */
    JsonApiResponseDTO<ProductResponseDTO> findById(UUID id);

    /**
     * This method is used to update a product by id
     * @param id id
     * @param saveNewProductDTO SaveNewProductDTO
     * @return HttpStatus
     */
    HttpStatus updateById(UUID id, SaveNewProductDTO saveNewProductDTO);

    /**
     * This method is used to delete a product by id
     * @param id id
     * @return HttpStatus
     */
    HttpStatus deleteById(UUID id);
}
