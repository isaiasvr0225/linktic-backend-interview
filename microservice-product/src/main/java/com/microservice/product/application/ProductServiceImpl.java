package com.microservice.product.application;

import com.microservice.product.domain.Product;
import com.microservice.product.domain.exceptions.ProductException;
import com.microservice.product.infrastructure.dto.*;
import com.microservice.product.infrastructure.http_client.InventoryHttpClient;
import com.microservice.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @package : com.microservice.product.application
 * @name : ProductServiceImpl.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@Slf4j
@RequiredArgsConstructor
public @Service class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final InventoryHttpClient inventoryHttpClient;

    @Override
    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        Page<Product> productPage = this.productRepository.findAll(pageable);

        if (productPage.isEmpty()) {
            throw new ProductException("No hay Productos registrados", HttpStatus.NOT_FOUND);
        }

        log.info("Lista de productos: {}", productPage);

        return productPage.map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        ));
    }

    @Override
    public HttpStatus save(SaveNewProductDTO saveNewProductDTO) {
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name(saveNewProductDTO.name())
                .price(saveNewProductDTO.price())
                .build();

        InventoryAttributesDTO inventoryAttributesDTO = InventoryAttributesDTO.builder()
                .productId(product.getId())
                .quantity(saveNewProductDTO.initialQuantity())
                .build();

        var jsonApiData = JsonApiData.<InventoryAttributesDTO>builder()
                .type("inventory")
                .id(String.valueOf(product.getId()))
                .attributes(inventoryAttributesDTO)
                .build();
        var jsonApiRequest = JsonApiRequestDTO.<InventoryAttributesDTO>builder()
                .data(jsonApiData)
                .build();

        this.inventoryHttpClient.save(jsonApiRequest).join();
        productRepository.save(product);

        return HttpStatus.CREATED;
    }

    @Override
    public JsonApiResponseDTO<ProductResponseDTO> findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found", HttpStatus.NOT_FOUND));

        ProductResponseDTO attributes = ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        JsonApiData<ProductResponseDTO> data = JsonApiData.<ProductResponseDTO>builder()
                .type("products")
                .id(String.valueOf(product.getId()))
                .attributes(attributes)
                .build();
        return JsonApiResponseDTO.<ProductResponseDTO>builder()
                .data(data)
                .build();
    }

    @Override
    public HttpStatus updateById(UUID id, SaveNewProductDTO saveNewProductDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found", HttpStatus.NOT_FOUND));

        product.setName(saveNewProductDTO.name());
        product.setPrice(saveNewProductDTO.price());

        productRepository.save(product);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus deleteById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found", HttpStatus.NOT_FOUND));

        productRepository.delete(product);
        return HttpStatus.OK;
    }
}
