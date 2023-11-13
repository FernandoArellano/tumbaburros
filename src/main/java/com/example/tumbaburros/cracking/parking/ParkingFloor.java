package com.example.tumbaburros.cracking.parking;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingFloor {
    List<Spot> spots;
    int floor;

    public ParkingFloor(List<Spot> spots, int floor) {
        this.spots = spots;
        this.floor = floor;
    }

    private Spot getAvailableSpotForVehicle(Vehicle vehicle){
        List<Spot> availableSpot = spots.stream().filter(i -> i.isFree() && i.getSize().equals(vehicle.getVehicleSize())).collect(Collectors.toList());
        if(availableSpot.size()>0){
            return availableSpot.get(0);
        }
        return null;
    }

    public boolean isFreeSpotForVehicle(Vehicle vehicle){
        return getAvailableSpotForVehicle(vehicle) != null;
    }

    public void assignSpotToVehicle(Vehicle vehicle){
        Spot spot = getAvailableSpotForVehicle(vehicle);
        if(spot != null){
            spot.assignVehicle(vehicle);
        }
    }

    public void removeVehicle(String plate){
        Spot spot = getVehicle(plate);
        if(spot != null){
            spot.removeVehicleFromSpot();
        }
    }

    public Spot getVehicle(String plate){
        Optional<Spot> optionalSpot = spots.stream().filter(i -> !i.isFree() && i.getCurrentVehicle().getPlate().equals(plate)).findFirst();
        return optionalSpot.get();
    }

}
