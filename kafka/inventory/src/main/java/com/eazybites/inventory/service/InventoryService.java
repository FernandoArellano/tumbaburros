package com.eazybites.inventory.service;

import com.eazybites.inventory.dto.InventoryResultEvent;
import com.eazybites.inventory.dto.OrderDto;
import com.eazybites.inventory.dto.OrderItemDto;
import com.eazybites.inventory.entity.FailedOrder;
import com.eazybites.inventory.entity.InventoryEntity;
import com.eazybites.inventory.exceptions.NonRetryableException;
import com.eazybites.inventory.exceptions.RetryableException;
import com.eazybites.inventory.repository.FailedOrderRepository;
import com.eazybites.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final FailedOrderRepository failedOrderRepository;

    public OrderDto updateInventory(OrderDto orderDto){

        boolean fullfilledOrder = true;

        for(OrderItemDto item : orderDto.getOrderItemDtos()){
            try{

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
            }catch (Exception e){
                handleException(e);
            }

        }

        orderDto.setStatus(fullfilledOrder ? "success": "failure");

        return orderDto;
    }

    public InventoryResultEvent sagaUpdateInventory(OrderDto orderDto){

        boolean fullfilledOrder = true;
        InventoryResultEvent result = new InventoryResultEvent();

        for(OrderItemDto item : orderDto.getOrderItemDtos()){
            try{

                Optional<InventoryEntity> optionalInventory = inventoryRepository.findByProductId(item.getProductId());

                InventoryEntity inventoryEntity = optionalInventory.orElse(null);

                if(inventoryEntity != null && inventoryEntity.getQuantityAvailable()>= item.getQuantity()){
                    //ya lo estoy descontando y aun no se si debo descontarlo
                    //podria incrementar escuchando otra topic
                    inventoryEntity.setQuantityAvailable(inventoryEntity.getQuantityAvailable()- item.getQuantity());
                    item.setStatus("RESERVED");
                    inventoryRepository.save(inventoryEntity);
                } else {
                    item.setStatus("FAILED");
                    fullfilledOrder = false;
                }

            }catch (Exception e){
                handleException(e);
            }

        }

        result.setOrderId(orderDto.getId());
        result.setItems(orderDto.getOrderItemDtos());
        result.setSuccess(fullfilledOrder);

        if (!fullfilledOrder) {
            result.setReason("Insufficient stock");
        }

        return result;
    }


    private void handleException(Exception e) {
        if(e instanceof IllegalArgumentException){
            throw new NonRetryableException("Invalid input: " +e.getMessage());
        } else if(e instanceof TimeoutException || e instanceof java.net.SocketTimeoutException){
            throw new RetryableException("Timeout occurred: " +e.getMessage());
        }

        throw new RetryableException("Generic Failure: " + e.getMessage());
    }

    public double getAvailabilityForProduct(String productId){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Optional<InventoryEntity> optionalInventoryEntity = inventoryRepository.findByProductId(productId);

        return optionalInventoryEntity.map(InventoryEntity::getQuantityAvailable).orElse(0.0);
    }


    public void saveFailedOrder(OrderDto orderDto, String errorMessage){
        FailedOrder failedOrder = new FailedOrder();
        failedOrder.setOrderDto(orderDto);
        failedOrder.setFailedAt(LocalDateTime.now());
        failedOrder.setErrorMessage(errorMessage);

        failedOrderRepository.save(failedOrder);
    }
}
