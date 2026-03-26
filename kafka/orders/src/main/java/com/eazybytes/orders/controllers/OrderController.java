package com.eazybytes.orders.controllers;

import com.eazybytes.orders.dto.OrderDto;
import com.eazybytes.orders.dto.OrderItemDto;
import com.eazybytes.orders.entity.Order;
import com.eazybytes.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody List<OrderItemDto> orderItemDtoList){
        OrderDto orderDto = getOrderDto(orderItemDtoList);

        OrderDto savedOrderDto = orderService.saveOrder(orderDto);

        return new ResponseEntity<>(savedOrderDto, HttpStatus.CREATED);
    }



    @PostMapping("/safeOrder")
    public ResponseEntity<OrderDto> createSafeOrder(@RequestBody List<OrderItemDto> orderItemDtoList){
        OrderDto orderDto = getOrderDto(orderItemDtoList);

        OrderDto savedOrderDto = orderService.saveSafeOrder(orderDto);

        return new ResponseEntity<>(savedOrderDto, HttpStatus.CREATED);
    }

    @PostMapping("/sagaOrder")
    public ResponseEntity<OrderDto> createSagaOrder(@RequestBody List<OrderItemDto> orderItemDtoList){
        OrderDto orderDto = getOrderDto(orderItemDtoList);

        OrderDto savedOrderDto = orderService.saveSagaOrder(orderDto);

        return new ResponseEntity<>(savedOrderDto, HttpStatus.CREATED);
    }

    private static @NonNull OrderDto getOrderDto(List<OrderItemDto> orderItemDtoList) {
        float total = orderItemDtoList.stream().map(o-> o.getPrice()*o.getQuantity()).mapToLong(Float::longValue).sum();
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderItemDtos(orderItemDtoList);
        orderDto.setTotal(total);
        orderDto.setStatus("PENDING");
        return orderDto;
    }

    @GetMapping("health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
