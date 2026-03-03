package com.example.week13.composing;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Replace Temp with Query
 * =============================================================================
 *
 * Intent:
 *   Replace a temporary variable that holds the result of an expression
 *   with a method (query) that returns the expression's result.
 *
 * When to use:
 *   - A temporary variable is used to store an intermediate result that
 *     is needed in multiple places within a method.
 *   - The expression assigned to the temp could be meaningful on its own.
 *   - Extracting it as a query makes it reusable and self-documenting.
 *
 * Mechanics:
 *   1. Identify a temp that is assigned once and used later.
 *   2. Extract the right-hand side expression into a new method (query).
 *   3. Replace all references to the temp with calls to the query.
 *   4. Remove the temp variable declaration.
 *
 * This demo shows a before/after comparison.
 * =============================================================================
 */
public class ReplaceTempWithQueryDemo {

    // =========================================================================
    // BEFORE: Intermediate values stored in temps
    // =========================================================================

    /**
     * BEFORE: Uses temporary variables to hold intermediate calculations.
     * If another method needs basePrice or discountFactor, it cannot access them.
     */
    static class OrderBefore {
        private int quantity;
        private double itemPrice;

        OrderBefore(int quantity, double itemPrice) {
            this.quantity = quantity;
            this.itemPrice = itemPrice;
        }

        double getPrice() {
            // Temp: basePrice
            double basePrice = quantity * itemPrice;

            // Temp: discountFactor
            double discountFactor;
            if (basePrice > 1000) {
                discountFactor = 0.95;  // 5% discount
            } else {
                discountFactor = 0.98;  // 2% discount
            }

            return basePrice * discountFactor;
        }
    }

    // =========================================================================
    // AFTER: Temps replaced with query methods
    // =========================================================================

    /**
     * AFTER: Temporary variables replaced by well-named query methods.
     * Each query is reusable and self-documenting.
     */
    static class OrderAfter {
        private int quantity;
        private double itemPrice;

        OrderAfter(int quantity, double itemPrice) {
            this.quantity = quantity;
            this.itemPrice = itemPrice;
        }

        /** Query: replaces the 'basePrice' temp variable. */
        private double basePrice() {
            return quantity * itemPrice;
        }

        /** Query: replaces the 'discountFactor' temp variable. */
        private double discountFactor() {
            return basePrice() > 1000 ? 0.95 : 0.98;
        }

        /** Now reads as: base price times discount factor. */
        double getPrice() {
            return basePrice() * discountFactor();
        }

        /** Bonus: these queries are now reusable elsewhere. */
        void printBreakdown() {
            System.out.println("      Base price:      $"
                    + String.format("%.2f", basePrice()));
            System.out.println("      Discount factor: "
                    + discountFactor());
            System.out.println("      Final price:     $"
                    + String.format("%.2f", getPrice()));
        }
    }

    /**
     * Demonstrates Replace Temp with Query refactoring.
     */
    public static void demo() {
        System.out.println("  [Replace Temp with Query - Before and After]");
        System.out.println();

        // Before
        System.out.println("    BEFORE (temps inside getPrice):");
        OrderBefore before1 = new OrderBefore(10, 80);
        System.out.println("      10 x $80 = $"
                + String.format("%.2f", before1.getPrice()));
        OrderBefore before2 = new OrderBefore(20, 80);
        System.out.println("      20 x $80 = $"
                + String.format("%.2f", before2.getPrice()));

        System.out.println();

        // After
        System.out.println("    AFTER (temps replaced with query methods):");
        OrderAfter after1 = new OrderAfter(10, 80);
        after1.printBreakdown();
        System.out.println();
        OrderAfter after2 = new OrderAfter(20, 80);
        after2.printBreakdown();

        System.out.println();
        System.out.println("    Query methods are reusable and self-documenting.");
    }
}
