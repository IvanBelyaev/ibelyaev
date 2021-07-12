package ru.job4j.auto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model of the car.
 */
@Entity
@Table(name = "models")
public class Model {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the model.
     */
    private String name;

    /**
     * Factory method.
     * @param name name of the model.
     * @return new model.
     */
    public static Model of(String name) {
        Model model = new Model();
        model.setName(name);
        return model;
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
     * Gets name of the model.
     * @return name of the model.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the model.
     * @param name new name of the model.
     */
    public void setName(String name) {
        this.name = name;
    }
}
