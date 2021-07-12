package ru.job4j.auto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Brand of the car.
 */
@Entity
@Table(name = "brands")
public class Brand {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the brand.
     */
    private String name;

    /**
     * List of the models of the brand.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();

    /**
     * Factory method.
     * @param name name of the brand.
     * @return new brand.
     */
    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return brand;
    }

    /**
     * Adds new model to the list.
     * @param model new model.
     */
    public void addModel(Model model) {
        this.models.add(model);
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
     * Gets name.
     * @return name of the brand.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name new name of the brand.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets all models of the brand.
     * @return all models of the brand.
     */
    public List<Model> getModels() {
        return models;
    }

    /**
     * Sets new list of the models.
     * @param models new list of the models.
     */
    public void setModels(List<Model> models) {
        this.models = models;
    }
}
