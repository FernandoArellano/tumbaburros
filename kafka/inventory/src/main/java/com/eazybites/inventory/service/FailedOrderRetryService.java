package com.eazybites.inventory.service;

import com.eazybites.inventory.entity.FailedOrder;
import com.eazybites.inventory.repository.FailedOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FailedOrderRetryService {

    private final FailedOrderRepository failedOrderRepository;
    private final StreamBridge streamBridge;

    @Scheduled(fixedDelay = 30000)
    public void retryFailedOrders(){

        List<FailedOrder> failedOrders = failedOrderRepository.findAll();

        for(FailedOrder failedOrder: failedOrders){
            try{
                if(failedOrder.getRetryCount()>=5){
                    continue;
                }

                streamBridge.send("notificationTopic", failedOrder.getOrderDto());

                failedOrderRepository.delete(failedOrder);

                System.out.println("retried successful");
            }
            catch(Exception e){
                failedOrder.setRetryCount(failedOrder.getRetryCount()+1);
                failedOrderRepository.save(failedOrder);
            }
        }

    }
}
