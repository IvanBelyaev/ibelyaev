package ru.job4j.distributor;

import org.junit.Test;
import ru.job4j.distributor.products.Fish;
import ru.job4j.distributor.products.Food;
import ru.job4j.distributor.products.Milk;
import ru.job4j.distributor.products.Pasta;
import ru.job4j.distributor.storages.Shop;
import ru.job4j.distributor.storages.Storage;
import ru.job4j.distributor.storages.Trash;
import ru.job4j.distributor.storages.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * ControlQualityTest.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class ControlQualityTest {
    /**
     * Test for the distribute method.
     */
    @Test
    public void whenDistributeThenProductsAreInAppropriateStorage() {
        LocalDate createDate = LocalDate.now().minusDays(10);
        Food pasta = new Pasta(
                "pasta",
                createDate.plusDays(100),
                createDate,
                300,
                20
        );
        Food pasta2 = new Pasta(
                "pasta2",
                createDate.plusDays(20),
                createDate,
                300,
                20
        );
        Food milk = new Milk(
                "milk",
                createDate.plusDays(12),
                createDate,
                300,
                20
        );
        Food fish = new Fish(
                "fish",
                createDate.plusDays(8),
                createDate,
                300,
                20
        );
        List<Food> products = new ArrayList<>();
        Collections.addAll(products, pasta, pasta2, milk, fish);
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        Collections.addAll(storages, shop, warehouse, trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.distribute(products);

        assertThat(shop.getProducts().size(), is(2));
        assertThat(warehouse.getProducts().size(), is(1));
        assertThat(trash.getProducts().size(), is(1));

        assertTrue(shop.getProducts().contains(pasta2));
        assertTrue(shop.getProducts().contains(milk));
        assertTrue(warehouse.getProducts().contains(pasta));
        assertTrue(trash.getProducts().contains(fish));

        assertThat(milk.getDiscount(), is(50.0));
    }

    /**
     * Test for the resort method.
     */
    @Test
    public void whenResortThenProductsAreInTheRightStorage() {
        LocalDate createDate = LocalDate.now().minusDays(10);
        Food pasta = new Pasta(
                "pasta",
                createDate.plusDays(100),
                createDate,
                300,
                20
        );
        Food pasta2 = new Pasta(
                "pasta2",
                createDate.plusDays(20),
                createDate,
                300,
                20
        );
        Food milk = new Milk(
                "milk",
                createDate.plusDays(12),
                createDate,
                300,
                20
        );
        Food fish = new Fish(
                "fish",
                createDate.plusDays(8),
                createDate,
                300,
                20
        );
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();

        warehouse.add(fish);
        warehouse.add(pasta2);
        trash.add(pasta);
        shop.add(milk);

        List<Storage> storages = new ArrayList<>();
        Collections.addAll(storages, shop, warehouse, trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.resort();

        assertThat(shop.getProducts().size(), is(2));
        assertThat(warehouse.getProducts().size(), is(1));
        assertThat(trash.getProducts().size(), is(1));

        assertTrue(shop.getProducts().contains(pasta2));
        assertTrue(shop.getProducts().contains(milk));
        assertTrue(warehouse.getProducts().contains(pasta));
        assertTrue(trash.getProducts().contains(fish));

        assertThat(milk.getDiscount(), is(50.0));
    }
}
