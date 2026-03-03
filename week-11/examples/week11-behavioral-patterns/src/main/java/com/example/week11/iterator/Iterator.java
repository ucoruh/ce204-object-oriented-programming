package com.example.week11.iterator;

/**
 * Iterator Pattern - Iterator Interface
 *
 * Provides a uniform interface for traversing different aggregate
 * structures (collections) without exposing their internal representation.
 *
 * Structure:
 *   <<interface>> Iterator<T>
 *       + hasNext(): boolean
 *       + next(): T
 *
 * Note: Java provides java.util.Iterator; this custom version
 * is for educational purposes to illustrate the pattern.
 *
 * @param <T> the type of elements returned by this iterator
 */
public interface Iterator<T> {

    /**
     * Returns true if the iteration has more elements.
     *
     * @return true if the iterator has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element
     */
    T next();
}
