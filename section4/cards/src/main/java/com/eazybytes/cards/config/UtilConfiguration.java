package com.eazybytes.cards.config;

import com.eazybytes.util.CustomLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class UtilConfiguration {

    @Bean(name="intRandom")
    public Random getRandom(){
        return new Random();
    }


    @Bean(name="longRandom")
    public Random getRandomLong(){
        return new Random(Long.parseLong("1597856"));
    }

    @Bean
    public CustomLogger getCustomLogger(DateConfigurationProperties dateConfigurationProperties){
        return new CustomLogger(dateConfigurationProperties);
    }

}
