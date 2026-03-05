package com.example.week11.strategy;

/**
 * Strategy Pattern - Strategy Interface
 *
 * Defines the common interface for all sorting algorithms.
 * Each concrete strategy implements a different sorting algorithm,
 * allowing the algorithm to be selected and swapped at runtime.
 *
 * Structure:
 *   <<interface>> SortStrategy
 *       + sort(array): int[]
 *       + getName(): String
 */
public interface SortStrategy {

    /**
     * Sorts the given array and returns the sorted result.
     * The original array may be modified in place.
     *
     * @param array the array of integers to sort
     * @return the sorted array
     */
    int[] sort(int[] array);

    /**
     * Returns the name of this sorting strategy.
     *
     * @return the strategy name
     */
    String getName();
}
