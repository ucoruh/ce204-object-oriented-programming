package com.example.week11.strategy;

/**
 * Strategy Pattern - Concrete Strategy: Merge Sort
 *
 * Implements the Merge Sort algorithm. This stable O(n log n)
 * divide-and-conquer algorithm divides the array in half,
 * recursively sorts each half, then merges the two sorted halves.
 *
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(n) auxiliary space
 * Best for: When stability is required, linked lists, external sorting
 */
public class MergeSort implements SortStrategy {

    @Override
    public int[] sort(int[] array) {
        int[] arr = array.clone(); // Don't modify original
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * Recursively divides and sorts the array.
     */
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted sub-arrays: arr[left..mid] and arr[mid+1..right].
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}
