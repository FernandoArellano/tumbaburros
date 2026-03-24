package com.eazybites.inventory.controller;

import com.eazybites.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {

    public final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<Double> getAvailabilityForProduct(@PathVariable String productId){
        double availability = inventoryService.getAvailabilityForProduct(productId);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }
}
