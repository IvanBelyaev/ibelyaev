package ru.job4j.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Engine.
 */
@Entity
public class Engine {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the engine.
     */
    @NotNull
    private String name;

    /**
     * Constructor for hibernate.
     */
    public Engine() {
    }

    /**
     * Constructor.
     * @param name name of the engine.
     */
    public Engine(String name) {
        this.name = name;
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
     * Gets the name of the engine.
     * @return the name of the engine.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the engine.
     * @param name new name of the engine.
     */
    public void setName(String name) {
        this.name = name;
    }
}
