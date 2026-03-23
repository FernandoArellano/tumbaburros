package com.eazybites.inventory.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InventoryDto {
    private String id;
    private String productId;
    private String productName;
    private double quantityAvailable;
}
