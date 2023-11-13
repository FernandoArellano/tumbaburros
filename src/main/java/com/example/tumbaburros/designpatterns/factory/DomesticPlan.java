package com.example.tumbaburros.designpatterns.factory;

public class DomesticPlan extends Plan {

    @Override
    double getRate() {
        rate = 3.5;
        return rate;
    }
}
