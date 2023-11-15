package com.example.tumbaburros.designpatterns.creational.factory;

public class CommercialPlan extends Plan{

    @Override
    double getRate() {
        rate = 7.5;
        return rate;
    }
}
