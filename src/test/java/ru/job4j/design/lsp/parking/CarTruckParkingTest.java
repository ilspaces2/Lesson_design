package ru.job4j.design.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTruckParkingTest {

    @Test
    public void whenNoFreePlacesThanFalse() {
        CarTruckParking parking = new CarTruckParking(0, 1);
        parking.parking(new Truck(2));
        assertFalse(parking.parking(new Car()));
        assertFalse(parking.parking(new Truck(2)));
    }

    @Test
    public void whenCarAndTruckParkingThenTrue() {
        CarTruckParking parking = new CarTruckParking(1, 1);
        assertTrue(parking.parking(new Car()));
        assertTrue(parking.parking(new Truck(2)));
    }

    @Test
    public void whenParkingTwoCarsAndOneTruck() {
        CarTruckParking parking = new CarTruckParking(2, 1);
        parking.parking(new Car());
        parking.parking(new Car());
        parking.parking(new Truck(2));
        assertEquals(parking.getCarPlaces(), 0);
        assertEquals(parking.getTruckPlaces(), 0);
    }

    @Test
    public void whenParkingTruckSizeTwoInCarPlacesAndTrackSizeTwoInTruckPlace() {
        CarTruckParking parking = new CarTruckParking(3, 1);
        parking.parking(new Truck(2));
        parking.parking(new Truck(2));
        assertEquals(parking.getCarPlaces(), 1);
        assertEquals(parking.getTruckPlaces(), 0);
    }

    @Test
    public void whenParkingTruckSizeTwoAndTruckSizeThreeInCarPlaces() {
        CarTruckParking parking = new CarTruckParking(5, 0);
        parking.parking(new Truck(2));
        parking.parking(new Truck(3));
        assertEquals(parking.getCarPlaces(), 0);
        assertEquals(parking.getTruckPlaces(), 0);
    }
}