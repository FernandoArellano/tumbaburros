package com.eazybytes.cards.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {


    private ServiceB serviceB;

    @Autowired
    public void setServiceB(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void methodA(){
        System.out.println("method A");
        serviceB.methodB();
    }
}
