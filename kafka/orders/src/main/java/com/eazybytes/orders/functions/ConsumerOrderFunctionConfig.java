package com.eazybytes.orders.functions;

import com.eazybytes.orders.dto.InventoryResultEvent;
import com.eazybytes.orders.entity.Order;
import com.eazybytes.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class ConsumerOrderFunctionConfig {

    private final OrderRepository orderRepository;

    @Bean
    public Consumer<InventoryResultEvent> inventoryResultConsumer(){
        return (inventoryResultEvent -> {

            Optional<Order> optionalOrder = orderRepository.findById(inventoryResultEvent.getOrderId());
            Order order = optionalOrder.orElse(null);

            if(order != null){

                if(inventoryResultEvent.isSuccess()){
                    order.setStatus("CONFIRMED");
                } else{
                    order.setStatus("CANCELLED");
                }

                orderRepository.save(order);
            }

        });
    }
}
