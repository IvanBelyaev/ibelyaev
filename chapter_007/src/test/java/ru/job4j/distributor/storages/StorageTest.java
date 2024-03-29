package ru.job4j.distributor.storages;

import org.junit.Test;
import ru.job4j.distributor.products.Fish;
import ru.job4j.distributor.products.Food;
import ru.job4j.distributor.products.Milk;
import ru.job4j.distributor.products.Pasta;

import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

/**
 * StorageTest.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class StorageTest {
    /**
     * Test for any class inherited from Storage.
     */
    @Test
    public void whenAddTwoFoodsAndDeleteOneFoodThenStorageContainsOneFood() {
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();
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

        assertFalse(shop.accept(pasta));
        assertTrue(shop.accept(pasta2));
        assertTrue(warehouse.accept(pasta));
        assertFalse(warehouse.accept(pasta2));
        assertFalse(trash.accept(pasta));
        assertTrue(trash.accept(fish));

        shop.add(pasta);
        shop.add(pasta2);
        shop.add(milk);
        warehouse.add(pasta);
        trash.add(fish);

        assertThat(shop.getProducts().size(), is(2));
        assertThat(warehouse.getProducts().size(), is(1));
        assertThat(trash.getProducts().size(), is(1));

        Set<Food> shopProducts = shop.extractProducts();
        Set<Food> warehouseProducts = warehouse.extractProducts();
        Set<Food> trashProducts = trash.extractProducts();

        assertThat(shopProducts.size(), is(2));
        assertThat(warehouseProducts.size(), is(1));
        assertThat(trashProducts.size(), is(1));
        assertThat(shop.getProducts().size(), is(0));
        assertThat(warehouse.getProducts().size(), is(0));
        assertThat(trash.getProducts().size(), is(0));
    }
}
