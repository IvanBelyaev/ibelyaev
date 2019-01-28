package ru.job4j.statistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    @Test
    public void when() {
        Store store = new Store();
        List<Store.User> previous = new ArrayList<>();
        previous.add(new Store.User(1, "Ivan"));
        previous.add(new Store.User(2, "Olga"));
        Store.User user = new Store.User(4, "Sergey");
        previous.add(user);

        List<Store.User> current = new ArrayList<>(previous);
        Store.User user2 = new Store.User(4, "Sergey2");
        current.set(current.indexOf(user), user2);
        current.add(new Store.User(3, "Viktor"));
        current.remove(0);
        Info info = store.diff(previous, current);

        assertThat(info.getNewUsers(), is(1));
        assertThat(info.getModifiedUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(1));
    }
}
