package com.example.tumbaburros.designpatterns.factory;

public abstract class Plan {

    protected double rate;
    abstract double getRate();

    public double calculateBill(int units){
        return units*rate;
    }
}
