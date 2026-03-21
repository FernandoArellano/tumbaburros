package com.eazybytes.notifications.entity;

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

    public NotificationEntity(long date, String customer, int amount) {
        this.date = date;
        this.customer = customer;
        this.amount = amount;
    }

    @Id
    private String id;

    private long date;
    private String customer;
    private int amount;
}
