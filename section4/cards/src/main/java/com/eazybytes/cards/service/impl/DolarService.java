package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.repository.DolarRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DolarService {

    private final DolarRepository dolarRepository;

    @Cacheable(value = "dolarPrice", key="#id")
    public double getDolarPrice(Long id){
        return dolarRepository.findById(id).get().getPrice();

    }
}
