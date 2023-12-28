package com.example.tumbaburros.java15;

public sealed class Vehicle permits Car, Bike, Truck {
    public Owner owner;

    public Vehicle(Owner owner){
        this.owner = owner;
    }

    public void vehicleOwner(){
        System.out.println("Vehicle owner: " + owner.name());
    }
}
