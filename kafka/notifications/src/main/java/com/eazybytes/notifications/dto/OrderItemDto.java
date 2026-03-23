package com.eazybytes.notifications.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemDto {
    private String productId;
    private String productName;
    private float price;
    private int quantity;
}
