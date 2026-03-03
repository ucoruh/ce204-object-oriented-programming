package com.example.week11.strategy;

import java.util.Arrays;

/**
 * Strategy Pattern - Context
 *
 * The Sorter maintains a reference to a SortStrategy and delegates
 * the sorting work to it. The strategy can be changed at runtime,
 * allowing the client to swap algorithms without modifying the context.
 *
 * Structure:
 *   Sorter (Context)
 *       - strategy: SortStrategy
 *       + setStrategy(strategy): void
 *       + sort(array): int[]
 */
public class Sorter {

    /** The current sorting strategy */
    private SortStrategy strategy;

    /**
     * Creates a Sorter with the given initial strategy.
     *
     * @param strategy the sorting strategy to use
     */
    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Changes the sorting strategy at runtime.
     *
     * @param strategy the new sorting strategy
     */
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sorts the given array using the current strategy and prints
     * the result for demonstration purposes.
     *
     * @param array the array to sort
     * @return the sorted array
     */
    public int[] sort(int[] array) {
        System.out.println("    Using strategy: " + strategy.getName());
        System.out.println("    Input:  " + Arrays.toString(array));
        int[] result = strategy.sort(array);
        System.out.println("    Output: " + Arrays.toString(result));
        return result;
    }
}
