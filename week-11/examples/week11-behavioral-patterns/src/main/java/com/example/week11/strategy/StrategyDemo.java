package com.example.week11.strategy;

/**
 * Strategy Pattern - Demo
 *
 * Intent:
 *   Define a family of algorithms, encapsulate each one, and make
 *   them interchangeable. Strategy lets the algorithm vary independently
 *   from the clients that use it.
 *
 * Structure:
 *   Sorter (Context) ---uses---> <<interface>> SortStrategy
 *                                       |
 *                            +----------+----------+
 *                            |          |          |
 *                       BubbleSort  QuickSort  MergeSort
 *
 * When to Use:
 *   - Many related classes differ only in their behavior (algorithm)
 *   - You need different variants of an algorithm
 *   - An algorithm uses data that clients shouldn't know about
 *   - A class defines many behaviors, appearing as multiple conditionals
 *
 * Real-World Examples:
 *   - Java Comparator for Collections.sort()
 *   - Payment processing (credit card, PayPal, crypto)
 *   - Compression algorithms (ZIP, GZIP, LZ4)
 *   - Route planning (fastest, shortest, scenic)
 *
 * Strategy vs. State:
 *   - Strategy: Client chooses the algorithm (no automatic transitions)
 *   - State: State objects transition to each other automatically
 */
public class StrategyDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 8: STRATEGY");
        System.out.println("  Define a family of interchangeable algorithms");
        System.out.println("==============================================================");

        int[] data = {64, 34, 25, 12, 22, 11, 90};

        // Create context with BubbleSort strategy
        Sorter sorter = new Sorter(new BubbleSort());

        System.out.println("\n  --- Sorting with Bubble Sort ---");
        sorter.sort(data);

        // Switch strategy to QuickSort at runtime
        System.out.println("\n  --- Switching to Quick Sort ---");
        sorter.setStrategy(new QuickSort());
        sorter.sort(data);

        // Switch strategy to MergeSort at runtime
        System.out.println("\n  --- Switching to Merge Sort ---");
        sorter.setStrategy(new MergeSort());
        sorter.sort(data);

        System.out.println();
    }
}
