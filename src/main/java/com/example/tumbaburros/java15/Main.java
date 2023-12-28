package com.example.tumbaburros.java15;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Vehicle monobike = new Monobike(new Owner("Fer"));

        Vehicle car = new Car(new Owner("Chachil"));

        List<Vehicle> vehicleList = new ArrayList<>(){{add(monobike); add(car);}};

        vehicleList.stream().peek(vehicle -> System.out.println(vehicle.owner.toString())).forEach(Vehicle::vehicleOwner);
    }
}
