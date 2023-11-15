package com.example.tumbaburros.designpatterns.creational.factory;

public class DomesticPlan extends Plan {

    @Override
    double getRate() {
        rate = 3.5;
        return rate;
    }
}
