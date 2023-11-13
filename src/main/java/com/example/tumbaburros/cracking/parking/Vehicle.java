package com.example.tumbaburros.cracking.parking;

public abstract class Vehicle {
    private final VehicleSize vehicleSize;
    private final String plate;

    public Vehicle(VehicleSize vehicleSize, String plate) {
        this.vehicleSize = vehicleSize;
        this.plate = plate;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public String getPlate() {
        return plate;
    }

}
