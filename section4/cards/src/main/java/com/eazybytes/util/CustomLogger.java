package com.eazybytes.util;

import com.eazybytes.cards.config.DateConfigurationProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

@RequiredArgsConstructor
public class CustomLogger {

    @Value("${example.property.value}")
    private String propertyValue;

    private final DateConfigurationProperties dateConfigurationProperties;



    @PostConstruct
    public void logStart(){
        System.out.println("start: " + Instant.now().toString());
        System.out.println("value property:" + propertyValue);
        System.out.println("dateConfiguration properties day: " + dateConfigurationProperties.getDay());
        System.out.println("dateConfiguration properties month: " + dateConfigurationProperties.getMonth());
        System.out.println("dateConfiguration properties year: " + dateConfigurationProperties.getYear());
    }

    public void logEnd(){
        System.out.println("end" + Instant.now().toString());
    }
}
