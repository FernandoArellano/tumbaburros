package com.eazybytes.orders.service;

import com.eazybytes.orders.dto.OrderDto;
import com.eazybytes.orders.dto.OrderItemDto;
import com.eazybytes.orders.entity.Order;
import com.eazybytes.orders.mapper.OrderToOrderDtoMapper;
import com.eazybytes.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private InventoryServiceClient inventoryServiceClient;
    private final StreamBridge streamBridge;

    public OrderDto saveOrder(OrderDto orderDto){
        Order order = OrderToOrderDtoMapper.orderDtoToOrder(orderDto);
        OrderDto savedOrderDto = OrderToOrderDtoMapper.orderToOrderDto(orderRepository.save(order));

//        streamBridge.send("produceOrder-out-0",
//                MessageBuilder
//                        .withPayload(savedOrderDto)
//                        .setHeader("content-type", "application/json")
//                        .build()
//                );

        streamBridge.send("produceOrder-out-0",savedOrderDto);

        return savedOrderDto;
    }

    public OrderDto saveSafeOrder(OrderDto orderDto){

        boolean validOrder = true;

        for(OrderItemDto item: orderDto.getOrderItemDtos()){
            System.out.println("Order Service saveSafeOrder: " +inventoryServiceClient.getClass());
            Double availability = inventoryServiceClient.validateInventoryForProduct(item.getProductId());
            if(availability < item.getQuantity()){
                item.setStatus("insufficient_stock");
                validOrder = false;
            }
        }

        if(!validOrder){
            orderDto.setStatus("failed");
            return orderDto;
        } else {
            return saveOrder(orderDto);
        }
    }

    public OrderDto saveSagaOrder(OrderDto orderDto){
        Order order = OrderToOrderDtoMapper.orderDtoToOrder(orderDto);
        OrderDto savedOrderDto = OrderToOrderDtoMapper.orderToOrderDto(orderRepository.save(order));

//        streamBridge.send("orderCreated-out-0",
//                MessageBuilder
//                        .withPayload(savedOrderDto)
//                        .setHeader("content-type", "application/json")
//                        .build()
//        );

        streamBridge.send("orderCreated-out-0",savedOrderDto);

        return savedOrderDto;
    }




}
