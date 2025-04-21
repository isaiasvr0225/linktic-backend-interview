package com.microservice.product.infrastructure.http_client;

import com.microservice.product.infrastructure.dto.InventoryAttributesDTO;
import com.microservice.product.infrastructure.dto.JsonApiRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "ms-inventory")
public interface InventoryHttpClient {

    @Async("asyncExecutor")
    @PostMapping("/api/v1/inventories")
    CompletableFuture<ResponseEntity<Void>> save(@RequestBody JsonApiRequestDTO<InventoryAttributesDTO> jsonApiRequestDTO);

}
