package com.example.week10.decorator;

/**
 * Concrete Decorator - SugarDecorator
 *
 * Adds sugar to the coffee. Extends the description and adds to the cost.
 *
 * In the Decorator pattern:
 *   - This is a "ConcreteDecorator" role
 *   - It adds its own behavior (sugar description + cost) to the wrapped Coffee
 */
public class SugarDecorator extends CoffeeDecorator {

    /**
     * Creates a sugar decorator wrapping the given coffee.
     *
     * @param coffee the coffee to add sugar to
     */
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription() + ", sugar";
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost() + 0.25;
    }
}
