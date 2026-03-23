package com.eazybytes.notifications.dto;

import com.eazybytes.notifications.model.NotificationEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NotificationDto {

    private String id;
    private OrderDto orderDto;
    private NotificationEnum notificationType;
    private long date;
}
