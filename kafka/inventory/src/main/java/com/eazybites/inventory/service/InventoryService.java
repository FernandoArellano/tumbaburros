package com.eazybites.inventory.service;

import com.eazybites.inventory.dto.OrderDto;
import com.eazybites.inventory.dto.OrderItemDto;
import com.eazybites.inventory.entity.InventoryEntity;
import com.eazybites.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public OrderDto updateInventory(OrderDto orderDto){

        boolean fullfilledOrder = true;

        for(OrderItemDto item : orderDto.getOrderItemDtos()){
            Optional<InventoryEntity> optionalInventory = inventoryRepository.findByProductId(item.getProductId());

            InventoryEntity inventoryEntity = optionalInventory.orElse(null);

            if(inventoryEntity == null){
                item.setStatus("product_not_found");
                fullfilledOrder = false;
            }
            else if(inventoryEntity.getQuantityAvailable()>= item.getQuantity()){
                inventoryEntity.setQuantityAvailable(inventoryEntity.getQuantityAvailable()- item.getQuantity());
                item.setStatus("success");
                inventoryRepository.save(inventoryEntity);
            } else {
                item.setStatus("insufficient");
                fullfilledOrder = false;
            }

        }

        orderDto.setStatus(fullfilledOrder ? "success": "failure");

        return orderDto;
    }

    public double getAvailabilityForProduct(String productId){
        Optional<InventoryEntity> optionalInventoryEntity = inventoryRepository.findByProductId(productId);

        return optionalInventoryEntity.map(InventoryEntity::getQuantityAvailable).orElse(0.0);
    }
}
