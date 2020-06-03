package ru.job4j.structures;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * UserStorage.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 03.06.2020
 */
@ThreadSafe
public class UserStorage {
    /**
     * Storage.
     */
    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    /**
     * Adds a user.
     * Only new users are added.
     * @param user new user.
     * @return true if user is added otherwise false.
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        int id = user.getId();
        if (storage.get(id) == null) {
            storage.put(id, user);
            result = true;
        }
        return result;
    }

    /**
     * Updates an existing user.
     * No new user added
     * @param user existing user
     * @return true if user exist into storage otherwise false.
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        int id = user.getId();
        if (storage.get(id) != null) {
            storage.put(id, user);
            result = true;
        }
        return result;
    }

    /**
     * Deletes user.
     * @param user user.
     * @return true if user deleted otherwise false.
     */
    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    /**
     * Transfer money from one user’s account to another.
     * @param fromId user from which money is transferred.
     * @param toId user into whose account money is transferred.
     * @param amount amount of money.
     * @return true if the transfer is completed otherwise false.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        User fromUser = storage.get(fromId);
        User toUser = storage.get(toId);
        if (fromUser != null && toUser != null && fromUser.getAmount() >= amount && amount > 0) {
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
        } else {
            result = false;
        }
        return result;
    }
}