package ru.job4j.design.lsp.parking;

public class CarTruckParking implements Parking {
    @Override
    public boolean parking(Vehicle vehicle) {
        return false;
    }
}
