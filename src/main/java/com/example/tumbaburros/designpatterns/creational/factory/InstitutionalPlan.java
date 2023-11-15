package com.example.tumbaburros.designpatterns.creational.factory;

public class InstitutionalPlan extends Plan{

    @Override
    double getRate() {
        rate = 5.5;
        return rate;
    }
}
