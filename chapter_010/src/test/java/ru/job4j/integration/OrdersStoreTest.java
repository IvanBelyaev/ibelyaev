package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for OrdersStore class.
 */
public class OrdersStoreTest {
    /**
     * Data source.
     */
    private BasicDataSource pool = new BasicDataSource();

    /**
     * Create database in memory.
     * @throws SQLException possible exceptions.
     */
    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests; sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("mm");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    /**
     * Deletes tables. Closes pool.
     * @throws SQLException possible exceptions.
     */
    @After
    public void deleteTables() throws SQLException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_002.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
        pool.close();
    }

    /**
     * Test for findAll method.
     */
    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertEquals(1, all.size());
        assertEquals("description1", all.get(0).getDescription());
        assertEquals(1, all.get(0).getId());
    }

    /**
     * Test for findById method.
     */
    @Test
    public void whenSaveOrdersAndFindByIdThenOneRowWithTheSameId() {
        OrdersStore store = new OrdersStore(pool);

        Order first = Order.of("name1", "description1");
        Order second = Order.of("name2", "description2");
        store.save(first);
        store.save(second);

        Order orderById = store.findById(second.getId());

        assertEquals(second.getId(), orderById.getId());
        assertEquals(second.getName(), orderById.getName());
        assertEquals(second.getDescription(), orderById.getDescription());
        assertEquals(second.getCreated(), orderById.getCreated());
    }

    /**
     * Test for updateOrder method.
     */
    @Test
    public void whenUpdateOrderThenOneOrderWasChanged() {
        OrdersStore store = new OrdersStore(pool);

        Order first = Order.of("name1", "description1");
        Order second = Order.of("name2", "description2");
        store.save(first);
        store.save(second);

        Order newOrder = Order.of("name3", "description3");
        store.updateOrder(newOrder, second.getId());

        Order updatedOrder = store.findById(second.getId());
        Order notUpdatedOrder = store.findById(first.getId());

        assertEquals(second.getId(), updatedOrder.getId());
        assertEquals(newOrder.getName(), updatedOrder.getName());
        assertEquals(newOrder.getDescription(), updatedOrder.getDescription());
        assertEquals(newOrder.getCreated(), updatedOrder.getCreated());
        assertEquals(first.getId(), notUpdatedOrder.getId());
        assertEquals(first.getName(), notUpdatedOrder.getName());
        assertEquals(first.getDescription(), notUpdatedOrder.getDescription());
        assertEquals(first.getCreated(), notUpdatedOrder.getCreated());
    }

    /**
     * Test for findByName method.
     */
    @Test
    public void whenFindByNameThenCollectionOfOrdersWithTheSameName() {
        OrdersStore store = new OrdersStore(pool);

        Order first = Order.of("name1", "description1");
        Order second = Order.of("name2", "description2");
        Order third = Order.of("name2", "description3");
        store.save(first);
        store.save(second);
        store.save(third);

        List<Order> ordersByName = (List<Order>) store.findByName("name2");

        assertEquals(2, ordersByName.size());
        assertEquals(second.getId(), ordersByName.get(0).getId());
        assertEquals("name2", ordersByName.get(0).getName());
        assertEquals(third.getId(), ordersByName.get(1).getId());
        assertEquals("name2", ordersByName.get(1).getName());
    }
}
