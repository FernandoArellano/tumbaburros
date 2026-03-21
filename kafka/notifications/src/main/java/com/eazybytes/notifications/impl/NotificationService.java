package com.eazybytes.notifications.impl;

import com.eazybytes.notifications.entity.NotificationEntity;
import com.eazybytes.notifications.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public String saveOrder(String msg){

        System.out.println("Saved order " + msg);
        NotificationEntity notificationEntity = new NotificationEntity(Instant.now().toEpochMilli(), msg, new Random().nextInt(0,1000));

        NotificationEntity savedEntity =  notificationRepository.save(notificationEntity);
        if(savedEntity!= null)
            return "inserted successfully with id: " +savedEntity.getId();

        return "failed";
    }
}
