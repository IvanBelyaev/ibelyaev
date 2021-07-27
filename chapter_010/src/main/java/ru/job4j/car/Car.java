package ru.job4j.car;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Car.
 */
@Entity
public class Car {
    /** Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the car.
     */
    @NotNull
    private String name;

    /**
     * The engine.
     */
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    /**
     * Drivers.
     */
    @ManyToMany(mappedBy = "cars", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Driver> drivers = new ArrayList<>();

    /**
     * Constructor for hibernate.
     */
    public Car() {
    }

    /**
     * Constructor.
     * @param name name of the car.
     * @param engine the engine.
     */
    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
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
     * Gets the name of the car.
     * @return the name of the car.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name to car.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets engine.
     * @return engine.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Sets engine.
     * @param engine new engine.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Gets drivers.
     * @return list of the drivers.
     */
    public List<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Sets new list of the drivers.
     * @param drivers new list of the drivers.
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
