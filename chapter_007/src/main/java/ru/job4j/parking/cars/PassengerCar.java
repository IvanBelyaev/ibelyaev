package ru.job4j.parking.cars;

/**
 * Car.
 * @author Ivan Belyaev
 * @since 21.01.2020
 * @version 2.0
 */
public interface PassengerCar extends Car {
    /**
     * The method returns the size of a passenger car.
     * @return the size of a passenger car.
     */
    @Override
    default int getSize() {
        return 1;
    }
}
