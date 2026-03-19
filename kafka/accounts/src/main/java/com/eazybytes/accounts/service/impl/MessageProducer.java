package com.eazybytes.accounts.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
    }
}
