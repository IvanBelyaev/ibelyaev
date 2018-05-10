package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleLinkedList.
 * @param <E> - the type of the elements of this container
 * @author Ivan Belyaev
 * @since 10.05.2018
 * @version 1.0
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    /** The first element of the container. */
    private Node first;
    /** The last element of the container. */
    private Node last;
    /** The size of the container. */
    private int size;
    /** Modification counter. */
    private int modCount;

    /**
     * The method adds an element to the container.
     * @param value - new element.
     */
    public void add(E value) {
        Node newLink = new Node(value);

        if (first == null) {
            first = newLink;
        } else {
            newLink.prev = last;
            last.next = newLink;
        }

        last = newLink;
        size++;
        modCount++;
    }

    /**
     * The method returns a container element.
     * @param index - the index of the desired element.
     * @return returns a container element.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node result;

        if (index < size / 2) {
            result = first;

            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = last;

            for (int i = size - 1; i > index; i--) {
                result = result.prev;
            }
        }

        return result.data;
    }

    /**
     * The method returns an iterator of the container.
     * @return returns an iterator of the container.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = first;
            private int position = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (current == null) {
                    throw new NoSuchElementException();
                }

                Node result = current;

                if (hasNext()) {
                    current = current.next;
                } else {
                    current = null;
                }

                position++;

                return result.data;
            }
        };
    }

    /**
     * Class storage container element.
     */
    private class Node {
        /** Data that is stored in the container. */
        private E data;
        /** Link to the next item. */
        private Node next;
        /** Link to the previous item. */
        private Node prev;

        /**
         * The constructor creates the object Node.
         * @param data - data that is stored in the container.
         */
        Node(E data) {
            this.data = data;
        }
    }
}
