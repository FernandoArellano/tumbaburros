package com.example.tumbaburros.cracking.parking;

import java.util.LinkedList;
import java.util.List;

public class ParkingLot {
    List<ParkingFloor> parkingFloors;

    public ParkingLot(){
        parkingFloors = new LinkedList<>();
    }

    public void addParkingFloor(ParkingFloor newFloor){
        parkingFloors.add(newFloor);
    }

    public int getNumberOfFloors(){
        return parkingFloors.size();
    }
}
