package com.example.tumbaburros.designpatterns.structural.bridge.restaurant;

public class ItalianRestaurant extends Restaurant{

    public ItalianRestaurant(Pizza pizza) {
        super(pizza);
    }

    @Override
    void addSauce() {
        pizza.setSauce("Oil");
    }

    @Override
    void addToppings() {
        pizza.setToppings(null);
    }

    @Override
    void makeCrust() {
        pizza.setCrust("Thin");
    }
}
