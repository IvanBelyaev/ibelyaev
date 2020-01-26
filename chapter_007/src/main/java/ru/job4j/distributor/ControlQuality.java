package ru.job4j.distributor;

import ru.job4j.distributor.products.Food;
import ru.job4j.distributor.storages.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ControlQuality.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class ControlQuality {
    /** Srorages. */
    private final Collection<Storage> storages;

    /**
     * Constructor creates a new ControlQuality object.
     * @param storages storages.
     */
    public ControlQuality(Collection<Storage> storages) {
        this.storages = storages;
    }

    /**
     * The method distributes the list of goods by storage.
     * @param products list of products.
     */
    public void distribute(List<Food> products) {
        for (Food food : products) {
            for (Storage storage : storages) {
                if (storage.accept(food)) {
                    storage.add(food);
                    break;
                }
            }
        }
    }

    /**
     * The method retrieves all products from the stores and redistributes them again.
     */
    public void resort() {
        List<Food> products = new ArrayList<>();
        storages.stream().forEach(storage -> { products.addAll(storage.extractProducts()); });
        distribute(products);
    }
}
