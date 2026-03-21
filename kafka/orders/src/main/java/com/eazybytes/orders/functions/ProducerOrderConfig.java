package com.eazybytes.orders.functions;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@AllArgsConstructor
public class ProducerOrderConfig {

    @Bean
    public Function<String, String> produceOrder(){
        return (msg)-> {

            return "orders notified msg: " +msg;
        };
    }

}
