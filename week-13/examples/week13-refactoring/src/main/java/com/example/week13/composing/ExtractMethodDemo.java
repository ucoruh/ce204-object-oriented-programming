package com.example.week13.composing;

import java.util.List;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Extract Method
 * =============================================================================
 *
 * Intent:
 *   Take a fragment of code that can be grouped together, turn it into a
 *   method whose name explains the purpose of the code.
 *
 * When to use:
 *   - A method is too long or does too many things.
 *   - A code fragment needs a comment to explain what it does.
 *   - The same fragment appears in multiple places.
 *
 * Mechanics:
 *   1. Create a new method and name it after what it does (not how).
 *   2. Copy the extracted code into the new method.
 *   3. Identify local variables -- pass as parameters or return values.
 *   4. Replace the original code with a call to the new method.
 *
 * This demo shows a before/after comparison side-by-side.
 * =============================================================================
 */
public class ExtractMethodDemo {

    // =========================================================================
    // BEFORE: All logic inlined in one method
    // =========================================================================

    /**
     * BEFORE: Printing an invoice with everything in one block.
     */
    static void printInvoiceBefore(String customer, List<Double> amounts) {
        System.out.println("    [BEFORE] ---- Invoice ----");
        System.out.println("    Customer: " + customer);

        // Calculate total (inline -- should be extracted)
        double total = 0;
        for (double amount : amounts) {
            total += amount;
        }

        // Determine discount (inline -- should be extracted)
        double discount = 0;
        if (total > 500) {
            discount = total * 0.10;
        } else if (total > 200) {
            discount = total * 0.05;
        }

        // Print summary (inline -- should be extracted)
        System.out.println("    Items:    " + amounts.size());
        System.out.println("    Subtotal: $" + String.format("%.2f", total));
        System.out.println("    Discount: -$" + String.format("%.2f", discount));
        System.out.println("    Total:    $"
                + String.format("%.2f", total - discount));
        System.out.println("    ---- End Invoice ----");
    }

    // =========================================================================
    // AFTER: Logic extracted into focused methods
    // =========================================================================

    /** Calculates the sum of all amounts. */
    private static double calculateTotal(List<Double> amounts) {
        double total = 0;
        for (double amount : amounts) {
            total += amount;
        }
        return total;
    }

    /** Determines the discount based on total amount. */
    private static double calculateDiscount(double total) {
        if (total > 500) return total * 0.10;
        if (total > 200) return total * 0.05;
        return 0;
    }

    /** Prints the invoice summary. */
    private static void printSummary(String customer, int itemCount,
                                      double subtotal, double discount) {
        System.out.println("    [AFTER]  ---- Invoice ----");
        System.out.println("    Customer: " + customer);
        System.out.println("    Items:    " + itemCount);
        System.out.println("    Subtotal: $" + String.format("%.2f", subtotal));
        System.out.println("    Discount: -$" + String.format("%.2f", discount));
        System.out.println("    Total:    $"
                + String.format("%.2f", subtotal - discount));
        System.out.println("    ---- End Invoice ----");
    }

    /**
     * AFTER: The orchestrator reads like a recipe.
     */
    static void printInvoiceAfter(String customer, List<Double> amounts) {
        double total = calculateTotal(amounts);
        double discount = calculateDiscount(total);
        printSummary(customer, amounts.size(), total, discount);
    }

    /**
     * Demonstrates Extract Method refactoring.
     */
    public static void demo() {
        System.out.println("  [Extract Method - Before and After]");
        System.out.println();

        List<Double> amounts = List.of(150.00, 250.00, 120.00);

        printInvoiceBefore("Alice", amounts);
        System.out.println();
        printInvoiceAfter("Alice", amounts);
        System.out.println();
        System.out.println("    The AFTER version is shorter and each piece");
        System.out.println("    is independently testable and reusable.");
    }
}
