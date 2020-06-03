package ru.job4j.structures;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * UserStorageTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 03.06.2020
 */
public class UserStorageTest {
    /**
     * Simple class for test.
     */
    private class StorageThread extends Thread {
        /**
         * UserStorage.
         * Common to all threads.
         */
        private final UserStorage userStorage;

        /**
         * Constructor.
         * @param userStorage user storage.
         */
        StorageThread(UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        /**
         * Repeatedly transfers money.
         */
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) {
                userStorage.transfer(1, 2, 1);
            }
        }
    }

    /**
     * Test for a successful money transfer.
     */
    @Test
    public void whenStorageHasTwoUsersWithEnoughMoneyThenTransferSuccessful() {
        UserStorage storage = new UserStorage();

        User first = new User(1, 100);
        User second = new User(2, 200);
        storage.add(first);
        storage.add(second);

        storage.transfer(1, 2, 50);
        assertThat(first.getAmount(), is(50));
        assertThat(second.getAmount(), is(250));
    }

    /**
     * When there is no second user in the repository, the transfer fails.
     */
    @Test
    public void whenStorageHasOnlyOneUserThenTransferNotSuccessful() {
        UserStorage storage = new UserStorage();

        User first = new User(1, 100);
        storage.add(first);

        boolean result = storage.transfer(1, 2, 50);
        assertThat(first.getAmount(), is(100));
        assertFalse(result);
    }

    /**
     * Test for the delete method.
     */
    @Test
    public void whenDeleteUserThenUserDeleted() {
        UserStorage storage = new UserStorage();

        User first = new User(1, 100);
        storage.add(first);
        assertTrue(storage.delete(first));
        assertFalse(storage.delete(first));
    }

    /**
     * Test for the update method.
     */
    @Test
    public void whenUpdateUserThenUserUpdated() {
        UserStorage storage = new UserStorage();

        User first = new User(1, 100);
        User second = new User(2, 200);
        storage.add(first);
        storage.add(second);

        User newFirstUser = new User(1, 300);
        storage.update(newFirstUser);
        storage.transfer(1, 2, 300);

        assertThat(first.getAmount(), is(100));
        assertThat(second.getAmount(), is(500));
        assertThat(newFirstUser.getAmount(), is(0));
    }

    /**
     * Test when money is being transferred in parallel.
     * @throws InterruptedException exceptions.
     */
    @Test
    public void whenConcurrentAccessToStorageThenDataIsNotCorrupted() throws InterruptedException {
        UserStorage storage = new UserStorage();

        User first = new User(1, 300_000);
        User second = new User(2, 0);
        storage.add(first);
        storage.add(second);

        Thread thread1 = new StorageThread(storage);
        Thread thread2 = new StorageThread(storage);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertThat(first.getAmount(), is(100_000));
        assertThat(second.getAmount(), is(200_000));
    }
}
