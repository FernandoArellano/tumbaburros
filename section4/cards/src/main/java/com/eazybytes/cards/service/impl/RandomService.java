package com.eazybytes.cards.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {

    private final Random random;

    public RandomService(@Qualifier("intRandom")Random random) {
        this.random = random;
    }

    public Integer getNumber(){
        return this.random.nextInt(0,50);
    }
}
