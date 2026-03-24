package com.eazybytes.orders.service;

import com.eazybytes.orders.client.InventoryFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceClient {

    private InventoryFeignClient inventoryFeignClient;

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackCheckInventory")
    public Double validateInventoryForProduct(String productId){
        System.out.println(this.getClass());
        ResponseEntity<Double> availability = inventoryFeignClient.getInventoryForProduct(productId);

        return availability.getBody();
    }

    public Double fallbackCheckInventory(String productId, Throwable ex){
        System.out.println("Fallback triggered due to:" + ex.getMessage());
        return 0.0;
    }

}
