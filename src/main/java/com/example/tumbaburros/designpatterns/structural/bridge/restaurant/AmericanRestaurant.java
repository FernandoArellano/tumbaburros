package com.example.tumbaburros.designpatterns.structural.bridge.restaurant;

public class AmericanRestaurant extends Restaurant{

    public AmericanRestaurant(Pizza pizza) {
        super(pizza);
    }

    @Override
    void addSauce() {
        pizza.setSauce("Super secret Recipe");
    }

    @Override
    void addToppings() {
        pizza.setToppings("Everything");
    }

    @Override
    void makeCrust() {
        pizza.setCrust("Thick");
    }
}
