package com.example.week11.strategy;

/**
 * Strategy Pattern - Concrete Strategy: Quick Sort
 *
 * Implements the Quick Sort algorithm. This efficient O(n log n)
 * divide-and-conquer algorithm selects a pivot, partitions the array
 * around the pivot, and recursively sorts the sub-arrays.
 *
 * Time Complexity: O(n log n) average, O(n^2) worst case
 * Space Complexity: O(log n) stack space
 * Best for: General-purpose sorting, large datasets
 */
public class QuickSort implements SortStrategy {

    @Override
    public int[] sort(int[] array) {
        int[] arr = array.clone(); // Don't modify original
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * Recursively sorts the sub-array from index low to high.
     */
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array around a pivot (last element).
     * Elements smaller than the pivot go to the left; larger go to the right.
     *
     * @return the final index of the pivot
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
}
