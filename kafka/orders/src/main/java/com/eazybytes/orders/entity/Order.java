package com.eazybytes.orders.entity;

import com.eazybytes.orders.dto.OrderItemDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String id;
    private List<OrderItemDto> orderItemDtos;
    private float total;
}
