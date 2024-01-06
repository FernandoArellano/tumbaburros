package com.example.tumbaburros.java15;

public sealed class Bike extends Vehicle permits Monobike{
    public Bike(Owner owner) {
        super(owner,2);
    }
}
