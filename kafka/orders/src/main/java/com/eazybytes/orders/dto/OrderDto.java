package com.eazybytes.orders.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String id;
    private List<OrderItemDto> orderItemDtos;
    private float total;
}
