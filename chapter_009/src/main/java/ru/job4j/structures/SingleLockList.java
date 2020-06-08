package ru.job4j.structures;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicArray;

import java.util.Iterator;

/**
 * SingleLockList.
 *
 * @param <T> the type of the elements of this list.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 08.06.2020
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    /** Storage. */
    @GuardedBy("this")
    private DynamicArray<T> storage = new DynamicArray<>();

    /**
     * Adds value to storage.
     * @param value new value.
     */
    public synchronized void add(T value) {
        storage.add(value);
    }

    /**
     * Gets the value from the store at the specified index.
     * @param index value index.
     * @return the value from the store.
     */
    public synchronized T get(int index) {
        return storage.get(index);
    }

    /**
     * The method returns an iterator of the storage.
     * @return an iterator of the storage.
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.storage).iterator();
    }

    /**
     * Copies dynamic array.
     * @param storage what you need to copy.
     * @return copy of dynamic array.
     */
    private synchronized DynamicArray<T> copy(DynamicArray<T> storage) {
        DynamicArray<T> storageCopy = new DynamicArray<>();
        for (T elem : storage) {
            storageCopy.add(elem);
        }
        return storageCopy;
    }
}
