package com.example.tumbaburros.designpatterns.structural.bridge.restaurant;

public class VeggiePizza extends Pizza{
    @Override
    public void assemble() {
        System.out.println("Adding sauce:"  + sauce);
        System.out.println("Adding toppings:"  + toppings);
        System.out.println("Adding Cheese");
    }

    @Override
    public void qualityCheck() {
        System.out.println("Crust is: " + crust);
    }
}
