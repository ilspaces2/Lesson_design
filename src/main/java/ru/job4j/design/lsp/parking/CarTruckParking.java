package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarTruckParking implements Parking {

    private int carPlaces;
    private int truckPlaces;
    private List<Vehicle> parkingList = new ArrayList<>();

    public CarTruckParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    public int getCarPlaces() {
        return carPlaces;
    }

    public int getTruckPlaces() {
        return truckPlaces;
    }

    public List<Vehicle> getParkingList() {
        return List.copyOf(parkingList);
    }

    @Override
    public boolean parking(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is null");
        }
        boolean rzl = false;
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == Car.CAR_SIZE && carPlaces > 0) {
            parkingList.add(vehicle);
            carPlaces--;
            rzl = true;
        } else if (vehicleSize > Car.CAR_SIZE && truckPlaces > 0) {
            parkingList.add(vehicle);
            truckPlaces--;
            rzl = true;
        } else if (vehicleSize > Car.CAR_SIZE && truckPlaces == 0 && vehicleSize <= carPlaces) {
            parkingList.add(vehicle);
            carPlaces -= vehicleSize;
            rzl = true;
        }
        return rzl;
    }
}
