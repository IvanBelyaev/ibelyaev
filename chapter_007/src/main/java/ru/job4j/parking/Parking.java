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
    /** Parking places. */
    private final Car[] parkingPlaces;
    /** Number of free parking spaces. */
    private int freePlaces;
    /** The total number of cars in the parking lot. */
    private int numberOfCars = 0;

    /**
     * Constructor.
     * @param parkingPlaces number of parking spaces.
     */
    public Parking(int parkingPlaces) {
        this.parkingPlaces = new Car[parkingPlaces];
        this.freePlaces = parkingPlaces;
    }

    /**
     * Method parks cars.
     * @param car any type of car, truck or passenger.
     * @return true if the car managed to park, otherwise returns false.
     */
    public boolean park(Car car) {
        boolean result = false;
        int startIndex = findFreePlaces(car);
        if (startIndex != -1) {
            for (int i = startIndex; i < startIndex + car.getSize(); i++) {
                parkingPlaces[i] = car;
            }
            freePlaces = freePlaces - car.getSize();
            numberOfCars++;
            result = true;
        }
        return result;
    }

    /**
     * Method returns amount of free places.
     * @return amount of free places.
     */
    public int getAmountOfFreePlaces() {
        return freePlaces;
    }

    /**
     * The method returns the number of parked cars, trucks and cars.
     * @return the number of parked car.
     */
    public int getNumberOfCars() {
        return numberOfCars;
    }

    /**
     * The method looks for a parking places.
     * @param car any type of car, truck or passenger.
     * @return the parking start location or -1 if there is no space.
     */
    private int findFreePlaces(Car car) {
        int startParkingWith = -1;
        int counter = 0;
        boolean isPreviousPlaceFree = false;
        for (int i = 0; i < parkingPlaces.length; i++) {
            if (parkingPlaces[i] == null) {
                if (!isPreviousPlaceFree) {
                    counter = 0;
                    isPreviousPlaceFree = true;
                }
                counter++;
            } else {
                startParkingWith = -1;
                isPreviousPlaceFree = false;
            }
            if (counter == car.getSize()) {
                startParkingWith = i - car.getSize() + 1;
                break;
            }
        }
        return startParkingWith;
    }
}
