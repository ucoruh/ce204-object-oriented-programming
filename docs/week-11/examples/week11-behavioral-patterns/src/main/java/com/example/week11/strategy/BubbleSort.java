package com.example.week11.strategy;

/**
 * Strategy Pattern - Concrete Strategy: Bubble Sort
 *
 * Implements the Bubble Sort algorithm. This simple O(n^2) algorithm
 * repeatedly steps through the list, compares adjacent elements,
 * and swaps them if they are in the wrong order.
 *
 * Time Complexity: O(n^2) average and worst case
 * Space Complexity: O(1)
 * Best for: Small datasets, educational purposes
 */
public class BubbleSort implements SortStrategy {

    @Override
    public int[] sort(int[] array) {
        int[] arr = array.clone(); // Don't modify original
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Optimization: if no swaps occurred, array is sorted
            if (!swapped) break;
        }

        return arr;
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
