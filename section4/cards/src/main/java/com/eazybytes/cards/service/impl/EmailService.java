package com.eazybytes.cards.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmailService {

    @Async
    public void sendEmail(String message, Long id){
        try {
            Thread.sleep(2000);
            System.out.println("Email sent: " + message + " id: " + id + " instant: " + Instant.now().getNano());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }
}
