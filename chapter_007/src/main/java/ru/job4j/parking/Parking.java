package ru.job4j.parking;

import ru.job4j.parking.cars.Car;

/**
 * Parking.
 * Parking class for trucks and cars.
 * @author Ivan Belyaev
 * @since 21.01.2020
 * @version 2.0
 */
public class Parking {
    /**
     * Constructor.
     * @param passengerCarParkingPlaces number of parking spaces for cars.
     * @param truckParkingPlaces number of parking spaces for trucks.
     */
    public Parking(int passengerCarParkingPlaces, int truckParkingPlaces) {

    }

    /**
     * Method parks cars.
     * Cars can only be parked in places for cars.
     * Trucks in the absence of free parking spaces for trucks are parked in places for cars.
     * @param car any type of car, truck or passenger.
     * @return true if the car managed to park, otherwise returns false.
     */
    boolean park(Car car) {
        return true;
    }

    /**
     * Method returns amount of free places for passenger car.
     * @return amount of free places for passenger car.
     */
    int getAmountOfFreePlacesForPassengerCar() {
        return 0;
    }

    /**
     * Method returns amount of free places for truck.
     * @return amount of free places for passenger truck.
     */
    int getAmountOfFreePlacesForTruck() {
        return 0;
    }

    /**
     * the method returns the number of parked cars, trucks and cars.
     * @return the number of parked car.
     */
    int getNumberOfCars() {
        return 0;
    }
}
