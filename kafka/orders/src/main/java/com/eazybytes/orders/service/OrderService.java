package com.eazybytes.orders.service;

import com.eazybytes.orders.dto.OrderDto;
import com.eazybytes.orders.entity.Order;
import com.eazybytes.orders.mapper.OrderToOrderDtoMapper;
import com.eazybytes.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto saveOrder(OrderDto orderDto){
        Order order = OrderToOrderDtoMapper.orderDtoToOrder(orderDto);
        return OrderToOrderDtoMapper.orderToOrderDto(orderRepository.save(order));
    }

}
