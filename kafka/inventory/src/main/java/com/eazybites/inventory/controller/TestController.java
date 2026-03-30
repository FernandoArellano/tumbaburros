package com.eazybites.inventory.controller;

import com.eazybites.inventory.util.Tests;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory/tests")
@AllArgsConstructor
public class TestController {

    private final Tests t;

    @GetMapping
    public ResponseEntity<String> runTest(){
        t.printAppNameWithValue();
        return ResponseEntity.ok("ok");
    }
}
