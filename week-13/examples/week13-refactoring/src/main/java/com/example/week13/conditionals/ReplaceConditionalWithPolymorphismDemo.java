package com.example.week13.conditionals;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Replace Conditional with Polymorphism
 * =============================================================================
 *
 * Intent:
 *   Replace a conditional that chooses different behavior based on type
 *   with polymorphic method calls.  Each type variant becomes a subclass
 *   that overrides the method.
 *
 * When to use:
 *   - You have a conditional (switch/if-else) that branches on a type code.
 *   - The same conditional appears in multiple methods.
 *   - Adding a new type requires changing every conditional.
 *
 * Mechanics:
 *   1. Create a subclass for each leg of the conditional.
 *   2. Move each branch's code into the corresponding subclass override.
 *   3. Make the base class method abstract (or provide a default).
 *   4. Delete the conditional in the original method.
 *
 * This demo shows shipping cost calculation before/after.
 * =============================================================================
 */
public class ReplaceConditionalWithPolymorphismDemo {

    // =========================================================================
    // BEFORE: Conditionals on shipping type
    // =========================================================================

    /**
     * BEFORE: Every method uses if-else on the shipping type string.
     */
    static class ShippingCalculatorBefore {
        static double cost(String shippingType, double weight) {
            if (shippingType.equals("GROUND")) {
                return weight * 1.50;
            } else if (shippingType.equals("AIR")) {
                return weight * 3.00 + 5.00;
            } else if (shippingType.equals("EXPRESS")) {
                return weight * 5.00 + 10.00;
            } else {
                throw new IllegalArgumentException("Unknown: " + shippingType);
            }
        }

        static int estimatedDays(String shippingType) {
            if (shippingType.equals("GROUND")) {
                return 7;
            } else if (shippingType.equals("AIR")) {
                return 3;
            } else if (shippingType.equals("EXPRESS")) {
                return 1;
            } else {
                throw new IllegalArgumentException("Unknown: " + shippingType);
            }
        }
    }

    // =========================================================================
    // AFTER: Polymorphic shipping strategies
    // =========================================================================

    /** Abstract shipping method -- each subclass provides its own logic. */
    static abstract class ShippingMethod {
        abstract double cost(double weight);
        abstract int estimatedDays();
        abstract String name();
    }

    static class GroundShipping extends ShippingMethod {
        @Override double cost(double weight) { return weight * 1.50; }
        @Override int estimatedDays() { return 7; }
        @Override String name() { return "Ground"; }
    }

    static class AirShipping extends ShippingMethod {
        @Override double cost(double weight) { return weight * 3.00 + 5.00; }
        @Override int estimatedDays() { return 3; }
        @Override String name() { return "Air"; }
    }

    static class ExpressShipping extends ShippingMethod {
        @Override double cost(double weight) { return weight * 5.00 + 10.00; }
        @Override int estimatedDays() { return 1; }
        @Override String name() { return "Express"; }
    }

    /**
     * Demonstrates Replace Conditional with Polymorphism.
     */
    public static void demo() {
        System.out.println("  [Replace Conditional with Polymorphism - Before and After]");
        System.out.println();

        double weight = 10.0;  // 10 kg package

        // Before
        System.out.println("    BEFORE (if-else on type string):");
        for (String type : new String[]{"GROUND", "AIR", "EXPRESS"}) {
            double cost = ShippingCalculatorBefore.cost(type, weight);
            int days = ShippingCalculatorBefore.estimatedDays(type);
            System.out.println("      " + type + ": $"
                    + String.format("%.2f", cost) + ", " + days + " days");
        }

        System.out.println();

        // After
        System.out.println("    AFTER (polymorphic subclasses):");
        ShippingMethod[] methods = {
            new GroundShipping(), new AirShipping(), new ExpressShipping()
        };
        for (ShippingMethod m : methods) {
            System.out.println("      " + m.name() + ": $"
                    + String.format("%.2f", m.cost(weight))
                    + ", " + m.estimatedDays() + " days");
        }

        System.out.println();
        System.out.println("    Adding a new shipping method (e.g., Drone) requires");
        System.out.println("    only ONE new subclass -- no existing code changes.");
    }
}
