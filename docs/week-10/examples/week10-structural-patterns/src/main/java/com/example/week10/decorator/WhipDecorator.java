package com.example.week10.decorator;

/**
 * Concrete Decorator - WhipDecorator
 *
 * Adds whipped cream to the coffee. Extends the description and adds to the cost.
 *
 * In the Decorator pattern:
 *   - This is a "ConcreteDecorator" role
 *   - It adds its own behavior (whipped cream description + cost)
 */
public class WhipDecorator extends CoffeeDecorator {

    /**
     * Creates a whip decorator wrapping the given coffee.
     *
     * @param coffee the coffee to add whipped cream to
     */
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription() + ", whipped cream";
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost() + 0.75;
    }
}
