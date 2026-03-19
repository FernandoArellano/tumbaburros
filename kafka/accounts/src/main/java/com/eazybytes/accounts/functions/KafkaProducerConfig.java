package com.eazybytes.accounts.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@Configuration
public class KafkaProducerConfig {

//    @Bean
//    public Supplier<Message<String>> produceMessage(){
//        return () -> {
//            String message = "Hello Kafka at " + System.currentTimeMillis();
//            System.out.println("Producing message: " + message);
//            return MessageBuilder.withPayload(message).build();
//        };
//    }
}
