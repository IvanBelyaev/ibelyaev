package ru.job4j.parking;

import org.junit.Test;
import ru.job4j.parking.cars.Car;
import ru.job4j.parking.cars.Kamaz;
import ru.job4j.parking.cars.VWPolo;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * ParkingTest.
 * @author Ivan Belyaev
 * @since 21.01.2020
 * @version 2.0
 */
public class ParkingTest {
    /**
     * Test when we park passenger car.
     */
    @Test
    public void whenParkOnePoloThenAmountOfFreePlacesForPassengerCarDecreasesByOne() {
        Parking parking = new Parking(10, 10);
        Car polo = new VWPolo();

        assertTrue(parking.park(polo));
        assertThat(parking.getAmountOfFreePlacesForPassengerCar(), is(9));
        assertThat(parking.getAmountOfFreePlacesForTruck(), is(10));
        assertThat(parking.getNumberOfCars(), is(1));
    }

    /**
     * Test when we park truck.
     */
    @Test
    public void whenParkOneKamazThenAmountOfFreePlacesForTruckDecreasesByOne() {
        Parking parking = new Parking(10, 10);
        Car kamaz = new Kamaz();

        assertTrue(parking.park(kamaz));
        assertThat(parking.getAmountOfFreePlacesForPassengerCar(), is(10));
        assertThat(parking.getAmountOfFreePlacesForTruck(), is(9));
        assertThat(parking.getNumberOfCars(), is(1));
    }

    /**
     * Test when the truck is parked in the passenger car seat.
     */
    @Test
    public void whenPlacesForTrucksEndThenParkOnPlacesForPassengerCar() {
        Parking parking = new Parking(10, 1);
        Car kamaz = new Kamaz();
        Car kamaz2 = new Kamaz();

        assertTrue(parking.park(kamaz));
        assertTrue(parking.park(kamaz2));
        assertThat(parking.getAmountOfFreePlacesForPassengerCar(), is(7));
        assertThat(parking.getAmountOfFreePlacesForTruck(), is(0));
        assertThat(parking.getNumberOfCars(), is(2));
    }

    /**
     * Test when the parking spaces have ended.
     */
    @Test
    public void whenParkingSpacesHaveEndedThenDoNotPark() {
        Parking parking = new Parking(1, 1);
        Car kamaz = new Kamaz();
        Car kamaz2 = new Kamaz();
        Car polo = new VWPolo();
        Car polo2 = new VWPolo();

        assertTrue(parking.park(kamaz));
        assertFalse(parking.park(kamaz2));
        assertTrue(parking.park(polo));
        assertFalse(parking.park(polo2));
        assertThat(parking.getAmountOfFreePlacesForPassengerCar(), is(0));
        assertThat(parking.getAmountOfFreePlacesForTruck(), is(0));
        assertThat(parking.getNumberOfCars(), is(2));
    }
}
