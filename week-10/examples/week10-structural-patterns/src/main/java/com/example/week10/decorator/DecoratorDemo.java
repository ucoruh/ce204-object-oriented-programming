package com.example.week10.decorator;

/**
 * =============================================================================
 * DECORATOR PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Attach additional responsibilities to an object dynamically.
 *   Decorators provide a flexible alternative to subclassing for
 *   extending functionality.
 *
 * Structure:
 *   [Component]  <--- interface
 *      |      \
 *   [Concrete   [Decorator]  <--- wraps a Component
 *    Component]    |     \
 *              [ConcreteDecoratorA]
 *              [ConcreteDecoratorB]
 *
 * Participants in this demo:
 *   - Component:           Coffee
 *   - ConcreteComponent:   SimpleCoffee
 *   - Decorator:           CoffeeDecorator (abstract)
 *   - ConcreteDecorators:  MilkDecorator, SugarDecorator, WhipDecorator
 *
 * When to Use:
 *   - Add responsibilities to objects dynamically and transparently
 *   - When extension by subclassing is impractical (too many combinations)
 *   - Java I/O streams are a classic example: BufferedReader wraps FileReader
 *
 * Key Benefit:
 *   Without Decorator: CoffeeWithMilk, CoffeeWithSugar, CoffeeWithMilkAndSugar,
 *   CoffeeWithMilkAndWhip... (combinatorial explosion of subclasses!)
 *   With Decorator: Stack any combination at runtime.
 * =============================================================================
 */
public class DecoratorDemo {

    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 4: DECORATOR");
        System.out.println("  Adding responsibilities to objects dynamically");
        System.out.println("-------------------------------------------------------------");

        // 1. Plain coffee - no decorators
        Coffee coffee1 = new SimpleCoffee();
        System.out.println("  Order 1: " + coffee1.getDescription());
        System.out.printf("    Cost: $%.2f%n", coffee1.getCost());
        System.out.println();

        // 2. Coffee with milk - one decorator
        Coffee coffee2 = new MilkDecorator(new SimpleCoffee());
        System.out.println("  Order 2: " + coffee2.getDescription());
        System.out.printf("    Cost: $%.2f%n", coffee2.getCost());
        System.out.println();

        // 3. Coffee with milk and sugar - two decorators stacked
        Coffee coffee3 = new SugarDecorator(
                new MilkDecorator(
                        new SimpleCoffee()));
        System.out.println("  Order 3: " + coffee3.getDescription());
        System.out.printf("    Cost: $%.2f%n", coffee3.getCost());
        System.out.println();

        // 4. Fully loaded: milk + sugar + whipped cream - three decorators
        Coffee coffee4 = new WhipDecorator(
                new SugarDecorator(
                        new MilkDecorator(
                                new SimpleCoffee())));
        System.out.println("  Order 4: " + coffee4.getDescription());
        System.out.printf("    Cost: $%.2f%n", coffee4.getCost());
        System.out.println();

        // 5. Double milk, double sugar (decorators can be applied multiple times)
        Coffee coffee5 = new SugarDecorator(
                new SugarDecorator(
                        new MilkDecorator(
                                new MilkDecorator(
                                        new SimpleCoffee()))));
        System.out.println("  Order 5 (double milk, double sugar): "
                + coffee5.getDescription());
        System.out.printf("    Cost: $%.2f%n", coffee5.getCost());

        System.out.println();
        System.out.println("  Key Takeaway: Decorators can be stacked in any combination.");
        System.out.println("  No need for separate subclasses for each combination.");
        System.out.println();
    }
}
