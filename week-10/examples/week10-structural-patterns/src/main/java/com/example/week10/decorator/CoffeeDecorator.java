package com.example.week10.decorator;

/**
 * Base Decorator - CoffeeDecorator
 *
 * Abstract base class for all coffee decorators. It implements the Coffee
 * interface and wraps another Coffee object (the "decoratee").
 *
 * In the Decorator pattern:
 *   - This is the "Decorator" (base) role
 *   - It maintains a reference to a Component object (wrappedCoffee)
 *   - It forwards requests to the wrapped object
 *   - Concrete decorators extend this class to add new behavior
 *
 * KEY CONCEPT:
 *   The decorator has the SAME interface as the component it wraps.
 *   This allows decorators to be stacked: each decorator wraps another
 *   Coffee (which could be a plain coffee or another decorator).
 *
 *   Example stacking:
 *     WhipDecorator -> MilkDecorator -> SimpleCoffee
 *     Each layer adds its own behavior on top of the wrapped object.
 */
public abstract class CoffeeDecorator implements Coffee {

    // The wrapped component - this IS the decorator pattern's core
    protected final Coffee wrappedCoffee;

    /**
     * Creates a decorator wrapping the given coffee.
     *
     * @param coffee the coffee to decorate (can be plain or already decorated)
     */
    protected CoffeeDecorator(Coffee coffee) {
        this.wrappedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost();
    }
}
