package com.eazybites.inventory.functions;

import com.eazybites.inventory.dto.InventoryResultEvent;
import com.eazybites.inventory.dto.OrderDto;
import com.eazybites.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

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

    @Bean
    public Consumer<Message<OrderDto>> dlqConsumer(){
        return message -> {
            System.out.println("message in DLQ + " +message.getPayload());
            OrderDto orderDto = message.getPayload();
            byte[] bytemessage = (byte[])message.getHeaders().get("x-exception-message");

            inventoryService.saveFailedOrder(orderDto, new String(bytemessage));
        };
    }

    @Bean
    public Function<OrderDto, InventoryResultEvent> sagaInventoryOrder(){
        return inventoryService::sagaUpdateInventory;
    }
}
