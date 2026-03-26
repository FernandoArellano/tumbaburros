package com.eazybytes.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryResultEvent {
    private String orderId;
    private boolean success;
    private List<OrderItemDto> items; // include item-level status
    private String reason;
}
