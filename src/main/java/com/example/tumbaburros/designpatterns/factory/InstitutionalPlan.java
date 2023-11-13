package com.example.tumbaburros.designpatterns.factory;

public class InstitutionalPlan extends Plan{

    @Override
    double getRate() {
        rate = 5.5;
        return rate;
    }
}
