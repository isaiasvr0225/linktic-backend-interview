package com.microservice.product.infrastructure.http_client;

import com.microservice.product.infrastructure.dto.InventoryAttributesDTO;
import com.microservice.product.infrastructure.dto.JsonApiRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "ms-inventory")
public interface InventoryHttpClient {

    @PostMapping("/api/v1/inventories")
    ResponseEntity<Void> save(@RequestBody JsonApiRequestDTO<InventoryAttributesDTO> jsonApiRequestDTO);

}
