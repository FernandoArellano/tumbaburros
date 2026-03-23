package com.eazybytes.notifications.functions;

import com.eazybytes.notifications.dto.OrderDto;
import com.eazybytes.notifications.impl.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class ConsumerNotificationConfig {

    private final NotificationService notificationService;

    @Bean
    public Consumer<OrderDto> notificationOrder(){
        return (orderDto)->{
            System.out.println("order received for notification");
            System.out.println(notificationService.saveOrder(orderDto));
        };
    }
}
