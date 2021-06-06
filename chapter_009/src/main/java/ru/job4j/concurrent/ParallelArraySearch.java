package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ParallelArraySearch.
 * @param <T> the type of the elements in the array.
 * @author Ivan Belyaev
 * @since 06.06.2021
 * @version 1.0
 */
public class ParallelArraySearch<T> extends RecursiveTask<Integer> {
    /** Threshold. */
    private static final int THRESHOLD = 10;
    /** Array. */
    private final T[] array;
    /** Searched element. */
    private final T elem;
    /** Start index. */
    private final int startIndex;
    /** End index. */
    private final int endIndex;

    /**
     * Constructor.
     * @param array array.
     * @param elem searched element.
     * @param startIndex start index.
     * @param endIndex end index.
     */
    private ParallelArraySearch(T[] array, T elem, int startIndex, int endIndex) {
        this.array = array;
        this.elem = elem;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * Launching a parallel search.
     * @param array array of elements.
     * @param elem searched element.
     * @param <E> the type of the elements in the array.
     * @return index of searched element otherwise returns -1.
     */
    public static <E> int find(E[] array, E elem) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelArraySearch<>(array, elem, 0, array.length - 1));
    }

    /**
     * Determines whether the search should be calculated linearly or divided into parallel subtasks.
     * @return index of searched element otherwise returns -1.
     */
    @Override
    protected Integer compute() {
        int arrLength = endIndex - startIndex;
        if (arrLength > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .filter(x -> x > 0)
                    .findAny()
                    .orElse(-1);
        } else {
            return processing(array, startIndex, endIndex);
        }
    }

    /**
     * Divides a task into subtasks.
     * @return collection of subtasks.
     */
    private Collection<ParallelArraySearch<T>> createSubtasks() {
        List<ParallelArraySearch<T>> dividedTasks = new ArrayList<>();
        int middleIndex = startIndex + (endIndex - startIndex) / 2;
        dividedTasks.add(new ParallelArraySearch(array, elem, startIndex, middleIndex));
        dividedTasks.add(new ParallelArraySearch(array, elem, middleIndex + 1, endIndex));
        return dividedTasks;
    }

    /**
     * Linear search for an element in a subarray.
     * @param arr array.
     * @param startIndex start index.
     * @param endIndex end index.
     * @return index of searched element otherwise returns -1.
     */
    private Integer processing(T[] arr, int startIndex, int endIndex) {
        int result = -1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (arr[i].equals(elem)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
