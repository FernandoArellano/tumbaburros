package com.example.tumbaburros.cracking.parking;

public class Spot {
    private final VehicleSize size;
    private Vehicle currentVehicle =null;

    public Spot(VehicleSize size) {
        this.size = size;
    }

    public boolean isFree(){
        return currentVehicle == null;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void assignVehicle(Vehicle vehicle){
        currentVehicle = vehicle;
    }

    public void removeVehicleFromSpot(){
        currentVehicle = null;
    }

    public Vehicle getCurrentVehicle(){
        return currentVehicle;
    }
}
