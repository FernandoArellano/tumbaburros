package com.eazybytes.accounts.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public Function<String, String> consumeMessage() {
        return message -> {
            System.out.println("Received message: " + message);
            return "Forwarded: " +message;
        };
    }

}
