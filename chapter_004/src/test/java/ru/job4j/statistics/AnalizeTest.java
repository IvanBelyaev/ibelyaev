package ru.job4j.statistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * AnalizeTest.
 * @author Ivan Belyaev
 * @since 28.01.2019
 * @version 1.0
 */
public class AnalizeTest {
    /**
     * First test for the diff method.
     */
    @Test
    public void whenTwoUsersAreAddedToTheListThenInInfoTheNumberOfNewUsersIsTwo() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Ivan"));
        List<Analize.User> current = new ArrayList<>(previous);
        current.add(new Analize.User(2, "Ira"));
        current.add(new Analize.User(3, "Sergey"));

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(2));
        assertThat(info.getModifiedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(0));
    }

    /**
     * Second test for the diff method.
     */
    @Test
    public void whenTwoUsersAreDeletedToTheListThenInInfoTheNumberOfDeletedUsersIsTwo() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Ivan"));
        previous.add(new Analize.User(2, "Ira"));
        previous.add(new Analize.User(3, "Sergey"));
        List<Analize.User> current = new ArrayList<>(previous);
        current.remove(0);
        current.remove(0);

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(2));
    }

    /**
     * Third test for the diff method.
     */
    @Test
    public void whenTwoUsersAreModifiedToTheListThenInInfoTheNumberOfModifiedUsersIsTwo() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Ivan"));
        previous.add(new Analize.User(2, "Ira"));
        previous.add(new Analize.User(3, "Sergey"));
        List<Analize.User> current = new ArrayList<>(previous);
        current.set(0, new Analize.User(1, "Sveta"));
        current.set(1, new Analize.User(2, "Oleg"));

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(2));
        assertThat(info.getDeletedUsers(), is(0));
    }

    /**
     * Fourth test for the diff method.
     */
    @Test
    public void whenTheListChangesThenChangeStatisticsChanges() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Ivan"));
        previous.add(new Analize.User(2, "Ira"));
        previous.add(new Analize.User(3, "Sergey"));
        List<Analize.User> current = new ArrayList<>(previous);

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(0));

        current.set(0, new Analize.User(1, "Sveta"));

        info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(0));

        current.remove(2);

        info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(1));

        current.add(new Analize.User(4, "Gleb"));

        info = analize.diff(previous, current);
        assertThat(info.getNewUsers(), is(1));
        assertThat(info.getModifiedUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(1));

    }
}
