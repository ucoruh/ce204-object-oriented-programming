package com.example.week10.decorator;

/**
 * Concrete Decorator - MilkDecorator
 *
 * Adds milk to the coffee. Extends the description and adds to the cost.
 *
 * In the Decorator pattern:
 *   - This is a "ConcreteDecorator" role
 *   - It adds its own behavior (milk description + cost) to the wrapped Coffee
 *   - It calls super methods to include the wrapped object's behavior
 */
public class MilkDecorator extends CoffeeDecorator {

    /**
     * Creates a milk decorator wrapping the given coffee.
     *
     * @param coffee the coffee to add milk to
     */
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription() + ", milk";
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost() + 0.50;
    }
}
