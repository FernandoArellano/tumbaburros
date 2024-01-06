package com.example.tumbaburros.java15;

public sealed class Vehicle permits Car, Bike, Truck {
    public Owner owner;
    public Tires numberOfTires;

    public Vehicle(Owner owner, int num){
        this.owner = owner;
        numberOfTires = new Tires(num);
    }

    public void vehicleOwner(){
        System.out.println("Vehicle owner: " + owner.name());
    }
}
