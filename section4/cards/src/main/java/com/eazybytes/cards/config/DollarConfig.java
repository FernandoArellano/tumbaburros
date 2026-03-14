package com.eazybytes.cards.config;

import com.eazybytes.cards.entity.DolarPrice;
import com.eazybytes.cards.repository.DolarRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DollarConfig {

    private final DolarRepository dolarRepository;

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            if(dolarRepository.findById(1L).isEmpty()){
                DolarPrice dolarPrice = new DolarPrice();
                dolarPrice.setPrice(100);
                dolarRepository.save(dolarPrice);
                System.out.println("added dolar price");
            }

        };
    }
}
