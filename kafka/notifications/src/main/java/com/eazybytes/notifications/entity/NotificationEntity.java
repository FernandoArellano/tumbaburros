package com.eazybytes.notifications.entity;

import com.eazybytes.notifications.dto.OrderDto;
import com.eazybytes.notifications.model.NotificationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity {


    @Id
    private String id;
    private OrderDto orderDto;
    private NotificationEnum notificationType;
    private long date;

}
