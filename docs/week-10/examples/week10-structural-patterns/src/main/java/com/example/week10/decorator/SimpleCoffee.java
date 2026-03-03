package com.example.week10.decorator;

/**
 * Concrete Component - SimpleCoffee
 *
 * The base object that can be "decorated" with additional responsibilities.
 * This represents a plain, no-frills coffee.
 *
 * In the Decorator pattern:
 *   - This is the "ConcreteComponent" role
 *   - It provides the default behavior that decorators will enhance
 *   - Decorators wrap this object (or other decorators) to add extras
 */
public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple coffee";
    }

    @Override
    public double getCost() {
        return 2.00;
    }
}
