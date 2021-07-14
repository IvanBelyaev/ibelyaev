package ru.job4j.auto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
     * Brand of the model.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

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

    /**
     * Gets the brand.
     * @return brand of the model.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Sets a brand to the model.
     * @param brand new brand.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Represents an object as a string.
     * @return a string representing this object.
     */
    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", brand=" + brand
                + '}';
    }
}
