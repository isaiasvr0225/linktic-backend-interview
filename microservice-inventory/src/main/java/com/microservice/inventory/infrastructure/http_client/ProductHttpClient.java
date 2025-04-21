package com.microservice.inventory.infrastructure.http_client;

import com.microservice.inventory.infrastructure.dto.JsonApiResponseDTO;
import com.microservice.inventory.infrastructure.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ms-product")
public interface ProductHttpClient {

    @GetMapping("/api/v1/products/{productId}")
    JsonApiResponseDTO<ProductResponseDTO> getProductById(@PathVariable(name = "productId") UUID productId);
}
