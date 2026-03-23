package com.eazybytes.orders.service;

import com.eazybytes.orders.dto.OrderDto;
import com.eazybytes.orders.entity.Order;
import com.eazybytes.orders.mapper.OrderToOrderDtoMapper;
import com.eazybytes.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final StreamBridge streamBridge;

    public OrderDto saveOrder(OrderDto orderDto){
        Order order = OrderToOrderDtoMapper.orderDtoToOrder(orderDto);
        OrderDto savedOrderDto = OrderToOrderDtoMapper.orderToOrderDto(orderRepository.save(order));

        streamBridge.send("produceOrder-out-0",
                MessageBuilder
                        .withPayload(savedOrderDto)
                        .setHeader("content-type", "application/json")
                        .build()
                );

        return savedOrderDto;
    }

}
