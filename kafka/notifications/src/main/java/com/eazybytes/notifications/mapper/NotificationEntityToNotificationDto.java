package com.eazybytes.notifications.mapper;

import com.eazybytes.notifications.dto.NotificationDto;
import com.eazybytes.notifications.entity.NotificationEntity;

public class NotificationEntityToNotificationDto {

    public static NotificationEntity notificationDtoToNotificationEntity(NotificationDto notificationDto){
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setId(notificationDto.getId());
        notificationEntity.setOrderDto(notificationDto.getOrderDto());
        notificationEntity.setDate(notificationEntity.getDate());
        notificationEntity.setNotificationType(notificationDto.getNotificationType());

        return notificationEntity;
    }

    public static NotificationDto notificationEntityToNotificationDto(NotificationEntity notificationEntity){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notificationEntity.getId());
        notificationDto.setOrderDto(notificationEntity.getOrderDto());
        notificationDto.setDate(notificationEntity.getDate());
        notificationDto.setNotificationType(notificationEntity.getNotificationType());

        return notificationDto;
    }
}
