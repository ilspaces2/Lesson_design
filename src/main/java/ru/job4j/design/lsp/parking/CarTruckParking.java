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
        return false;
    }
}
