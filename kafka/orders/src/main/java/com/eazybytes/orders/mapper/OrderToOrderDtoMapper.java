package com.eazybytes.orders.mapper;

import com.eazybytes.orders.dto.OrderDto;
import com.eazybytes.orders.entity.Order;

public class OrderToOrderDtoMapper {

    public static Order orderDtoToOrder(OrderDto orderDto){
        return new Order(orderDto.getId(), orderDto.getOrderItemDtos(), orderDto.getTotal());
    }

    public static OrderDto orderToOrderDto(Order order){
        return new OrderDto(order.getId(), order.getOrderItemDtos(), order.getTotal());
    }
}
