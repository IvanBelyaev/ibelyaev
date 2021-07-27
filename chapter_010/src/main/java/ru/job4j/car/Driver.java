package ru.job4j.car;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Driver.
 */
@Entity
public class Driver {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Driver's name.
     */
    @NotNull
    private String name;

    /**
     * List of the cars.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = { @JoinColumn(name = "driver_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_id") })
    private List<Car> cars = new ArrayList<>();

    /**
     * Constructor for hibernate.
     */
    public Driver() {
    }

    /**
     * Constructor.
     * @param name driver's name.
     */
    public Driver(String name) {
        this.name = name;
    }

    /**
     * Adds car to the list.
     * @param car new car.
     */
    public void addCar(Car car) {
        this.cars.add(car);
        car.getDrivers().add(this);
    }

    /**
     * Gets id.
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id new id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     * @return driver's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets driver's name.
     * @param name new driver's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets list of cars.
     * @return list of cars.
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Sets list of cars.
     * @param cars new list of cars.
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
