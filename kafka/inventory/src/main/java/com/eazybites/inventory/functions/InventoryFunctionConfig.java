package com.eazybites.inventory.functions;

import com.eazybites.inventory.dto.OrderDto;
import com.eazybites.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@AllArgsConstructor
public class InventoryFunctionConfig {

    private final InventoryService inventoryService;

    @Bean
    public Function<OrderDto, OrderDto> inventoryOrder(){
        return inventoryService::updateInventory;
    }
}
