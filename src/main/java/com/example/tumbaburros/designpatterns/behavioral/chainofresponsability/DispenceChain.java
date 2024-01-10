package com.example.tumbaburros.designpatterns.behavioral.chainofresponsability;

public interface DispenceChain {

    void setChain(DispenceChain chain);

    void dispense(Currency currency);
}
