package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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
        assertThat(parking.getCarPlaces(), is(0));
        assertThat(parking.getTruckPlaces(), is(0));
    }

    @Test
    public void whenParkingTruckSizeTwoInCarPlacesAndTrackSizeTwoInTruckPlace() {
        CarTruckParking parking = new CarTruckParking(3, 1);
        parking.parking(new Truck(2));
        parking.parking(new Truck(2));
        assertThat(parking.getCarPlaces(), is(1));
        assertThat(parking.getTruckPlaces(), is(0));
    }

    @Test
    public void whenParkingTruckSizeTwoAndTruckSizeThreeInCarPlaces() {
        CarTruckParking parking = new CarTruckParking(5, 0);
        parking.parking(new Truck(2));
        parking.parking(new Truck(3));
        assertThat(parking.getCarPlaces(), is(0));
        assertThat(parking.getTruckPlaces(), is(0));
    }
}