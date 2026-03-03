package com.example.week10.decorator;

/**
 * Component Interface - Coffee
 *
 * This interface defines the common operations for both plain coffees
 * and decorated coffees (coffees with extras like milk, sugar, whip).
 *
 * In the Decorator pattern:
 *   - This is the "Component" role
 *   - Both ConcreteComponent and Decorator implement this interface
 *   - The client works with this interface, treating plain and
 *     decorated objects identically
 */
public interface Coffee {

    /**
     * Returns a description of this coffee and its additions.
     *
     * @return human-readable description
     */
    String getDescription();

    /**
     * Returns the total cost of this coffee including all additions.
     *
     * @return cost in dollars
     */
    double getCost();
}
