package com.eazybytes.notifications.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {
    private String id;
    private List<OrderItemDto> orderItemDtos;
    private float total;
    private String status;
}
