package com.eazybytes.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryFeignClient {

    @GetMapping("/api/inventory/{productId}")
    ResponseEntity<Double> getInventoryForProduct(@PathVariable String productId);
}
