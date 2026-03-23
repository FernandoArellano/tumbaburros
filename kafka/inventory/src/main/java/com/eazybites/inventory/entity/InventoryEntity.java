package com.eazybites.inventory.entity;

import com.eazybites.inventory.util.InventoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryEntity {

    @Id
    private String id;
    private String productId;
    private String productName;
    private double quantityAvailable;

}
