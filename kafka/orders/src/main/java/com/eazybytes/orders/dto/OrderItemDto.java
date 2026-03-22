package com.eazybytes.orders.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private String productId;
    private String productName;
    private float price;
    private int quantity;
}
