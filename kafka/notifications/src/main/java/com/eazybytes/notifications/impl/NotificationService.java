package com.eazybytes.notifications.impl;

import com.eazybytes.notifications.dto.NotificationDto;
import com.eazybytes.notifications.dto.OrderDto;
import com.eazybytes.notifications.entity.NotificationEntity;
import com.eazybytes.notifications.mapper.NotificationEntityToNotificationDto;
import com.eazybytes.notifications.model.NotificationEnum;
import com.eazybytes.notifications.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationDto saveOrder(OrderDto orderDto){

        System.out.println("Notify about order " + orderDto);
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setOrderDto(orderDto);
        notificationEntity.setDate(Instant.now().toEpochMilli());
        notificationEntity.setNotificationType(LocalDate.now().getDayOfMonth()%2==0? NotificationEnum.SMS : NotificationEnum.EMAIL);

        NotificationEntity savedNotificationEntity = notificationRepository.save(notificationEntity);
        return NotificationEntityToNotificationDto.notificationEntityToNotificationDto(savedNotificationEntity);
    }
}
