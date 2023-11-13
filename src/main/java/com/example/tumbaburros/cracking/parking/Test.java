package com.example.tumbaburros.cracking.parking;

import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        ParkingLot parking = new ParkingLot();

        List<Spot> spots = fillParkingFloorSpots();
        ParkingFloor parkingFloor = new ParkingFloor(spots,1);

        parking.addParkingFloor(parkingFloor);

        for(int i=0; i<4; i++){
            Vehicle vehicle = new Bus("JFE10"+i);
            if(parkingFloor.isFreeSpotForVehicle(vehicle)){
                parkingFloor.assignSpotToVehicle(vehicle);
            }
        }

        for(int i=0; i<14; i++){
            Vehicle vehicle = new Car("BFE10"+i);
            if(parkingFloor.isFreeSpotForVehicle(vehicle)){
                parkingFloor.assignSpotToVehicle(vehicle);
            }
        }

        for(int i=0; i<5; i++){
            Vehicle vehicle = new MotorCycle("MFE10"+i);
            if(parkingFloor.isFreeSpotForVehicle(vehicle)){
                parkingFloor.assignSpotToVehicle(vehicle);
            }
        }

        parkingFloor.removeVehicle("MFE100");
        parkingFloor.removeVehicle("BFE100");
        parkingFloor.removeVehicle("JFE100");
    }

    private static List<Spot> fillParkingFloorSpots() {
        List<Spot> parkingFloor = new LinkedList<>();
        for(int i=0; i<10; i++){
            Spot compactSpot = new Spot(VehicleSize.COMPACT);
            Spot motorcycle = new Spot(VehicleSize.MOTORCYCLE);
            Spot busSpot = new Spot(VehicleSize.BUS);
            parkingFloor.add(compactSpot);

            if((i+10)%2==0){
                parkingFloor.add(motorcycle);
            }

            if((i+10)%3==0){
                parkingFloor.add(busSpot);
            }
        }
        return parkingFloor;
    }
}
