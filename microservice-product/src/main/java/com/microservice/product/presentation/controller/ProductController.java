package com.microservice.product.presentation.controller;

import com.microservice.product.application.ProductService;
import com.microservice.product.infrastructure.dto.JsonApiResponseDTO;
import com.microservice.product.infrastructure.dto.ProductResponseDTO;
import com.microservice.product.infrastructure.dto.SaveNewProductDTO;
import com.microservice.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * @package : com.microservice.product.presentation.controller
 * @name : ProductController.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public @RestController class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    //@PreAuthorize("permitAll()")
    @GetMapping("/count")
    public ResponseEntity<?> countProducts() {
        return  ResponseEntity.ok(this.productRepository.count());
    }

    //@PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.productService.findAll(pageable));
    }


    //@PreAuthorize("permitAll()")
    //@PreAuthorize("hasAnyRole('ADMIN', 'CARRIER')")
    @GetMapping("/{productId}")
    public ResponseEntity<JsonApiResponseDTO<ProductResponseDTO>> findById(@PathVariable(name = "productId") UUID productId) {
        return ResponseEntity.ok(this.productService.findById(productId));
    }


    //@PreAuthorize("permitAll()")
    @PostMapping
    public HttpStatus save(@RequestBody SaveNewProductDTO saveNewProductDTO) {

        return this.productService.save(saveNewProductDTO);
    }


    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{productId}")
    public HttpStatus update(@PathVariable(name = "productId") UUID productId, @RequestBody SaveNewProductDTO newProductDTO) {
        return this.productService.updateById(productId, newProductDTO);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'CARRIER')")
    @DeleteMapping("/{productId}")
    public HttpStatus delete(@PathVariable(name = "productId") UUID productId) {

        return this.productService.deleteById(productId);
    }


}
