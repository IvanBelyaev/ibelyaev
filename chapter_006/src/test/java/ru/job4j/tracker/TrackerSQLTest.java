package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TrackerSQLTest.
 * @author Ivan Belyaev
 * @since 13.11.2019
 * @version 1.0
 */
public class TrackerSQLTest {
    /**
     * Test for the add method.
     */
    @Test
    public void whenAddOneToOneThenTwo() {
        ITracker tracker = new TrackerSQL();
        Item item1 = new Item("name1", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("name2", "desc2", 1234L);
        tracker.add(item2);
        List<Item> methodReturns = tracker.findAll();

        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test for the update method.
     */
    @Test
    public void whenUpdateNameThenReturnNewName() {
        ITracker tracker = new TrackerSQL();
        Item item1 = new Item("name1", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("name2", "desc2", 1234L);
        item2.setId(item1.getId());
        tracker.replace(item2.getId(), item2);
        String expected = "name2";
        assertThat(tracker.findById(item1.getId()).getName(), is(expected));
    }

    /**
     * Test for the delete method.
     */
    @Test
    public void whenDeleteOneFromTwoThenOne() {
        ITracker tracker = new TrackerSQL();
        Item item1 = new Item("name1", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("name2", "desc2", 1234L);
        tracker.add(item2);
        tracker.delete(item1.getId());
        List<Item> methodReturns = tracker.findAll();

        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item2);

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test for the findAll method.
     */
    @Test
    public void whenFindAllTwoFromTwoThenTwo() {
        ITracker tracker = new TrackerSQL();
        Item item1 = new Item("name1", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("name2", "desc2", 1234L);
        tracker.add(item2);
        List<Item> methodReturns = tracker.findAll();

        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test for the findByName method.
     */
    @Test
    public void whenFindByNameAsTaskThenTwo() {
        ITracker tracker = new TrackerSQL();
        Item item1 = new Item("task", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("name", "desc2", 1234L);
        tracker.add(item2);
        Item item3 = new Item("task", "desc4", 12345L);
        tracker.add(item3);
        List<Item> methodReturns = tracker.findByName("task");

        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test for the findById method.
     */
    @Test
    public void whenFindByIdThenItemWithUniqueId() {
        ITracker tracker = new TrackerSQL();
        Item item1 = new Item("task", "desc1", 123L);
        tracker.add(item1);
        Item item2 = new Item("name", "desc2", 1234L);
        tracker.add(item2);
        Item item3 = new Item("task", "desc4", 12345L);
        tracker.add(item3);
        Item methodReturns = tracker.findById(item2.getId());
        Item expected = item2;
        assertThat(methodReturns, is(expected));
    }
}