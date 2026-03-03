package com.example.week12.bloaters;

/**
 * =============================================================================
 * CODE SMELL FIX: Long Method (AFTER Refactoring)
 * =============================================================================
 *
 * Solution Applied: Extract Method
 *
 *   The original monolithic processOrder() has been broken into small,
 *   well-named methods, each handling exactly one responsibility:
 *     - validateOrder()     -- input validation
 *     - calculateSubtotal() -- summing prices
 *     - calculateDiscount() -- membership-based discount
 *     - calculateTax()      -- tax computation
 *     - printReceipt()      -- formatted output
 *
 * Benefits:
 *   - Each method is easy to read and understand in isolation.
 *   - Individual methods can be tested independently.
 *   - Sub-steps can be reused elsewhere (e.g., calculateTax in reports).
 *   - The top-level method reads like a high-level summary.
 * =============================================================================
 */
public class LongMethodAfter {

    // =========================================================================
    // Validation
    // =========================================================================

    /**
     * Validates the order inputs.
     *
     * @return true if all inputs are valid, false otherwise
     */
    private static boolean validateOrder(String customerName, String[] items,
                                          double[] prices) {
        if (customerName == null || customerName.isEmpty()) {
            System.out.println("  [ERROR] Customer name is required.");
            return false;
        }
        if (items == null || items.length == 0) {
            System.out.println("  [ERROR] No items in the order.");
            return false;
        }
        if (items.length != prices.length) {
            System.out.println("  [ERROR] Items and prices arrays must match.");
            return false;
        }
        for (double price : prices) {
            if (price < 0) {
                System.out.println("  [ERROR] Prices cannot be negative.");
                return false;
            }
        }
        return true;
    }

    // =========================================================================
    // Calculations
    // =========================================================================

    /**
     * Calculates the subtotal by summing all item prices.
     */
    private static double calculateSubtotal(double[] prices) {
        double subtotal = 0;
        for (double price : prices) {
            subtotal += price;
        }
        return subtotal;
    }

    /**
     * Calculates the discount based on membership level.
     */
    private static double calculateDiscount(double subtotal,
                                             String membershipLevel) {
        if (membershipLevel == null) {
            return 0;
        }
        return switch (membershipLevel) {
            case "GOLD"   -> subtotal * 0.15;
            case "SILVER" -> subtotal * 0.10;
            case "BRONZE" -> subtotal * 0.05;
            default       -> 0;
        };
    }

    /**
     * Calculates the tax on the given amount.
     */
    private static double calculateTax(double amount) {
        double taxRate = 0.08;
        return amount * taxRate;
    }

    // =========================================================================
    // Output
    // =========================================================================

    /**
     * Prints a formatted receipt.
     */
    private static void printReceipt(String customerName, String[] items,
                                      double[] prices, double subtotal,
                                      double discount, double tax,
                                      double total) {
        System.out.println("  ========== RECEIPT ==========");
        System.out.println("  Customer: " + customerName);
        for (int i = 0; i < items.length; i++) {
            System.out.println("    " + items[i] + " - $"
                    + String.format("%.2f", prices[i]));
        }
        System.out.println("  Subtotal:  $" + String.format("%.2f", subtotal));
        System.out.println("  Discount:  -$" + String.format("%.2f", discount));
        System.out.println("  Tax:       $" + String.format("%.2f", tax));
        System.out.println("  TOTAL:     $" + String.format("%.2f", total));
        System.out.println("  ==============================");
    }

    // =========================================================================
    // Orchestrator -- reads like a high-level recipe
    // =========================================================================

    /**
     * GOOD EXAMPLE: The refactored processOrder method now reads like
     * a concise summary. Each step is delegated to a focused helper.
     */
    public static void processOrder(String customerName, String[] items,
                                     double[] prices, String membershipLevel) {
        // Step 1: Validate
        if (!validateOrder(customerName, items, prices)) {
            return;
        }
        System.out.println("  Order validated successfully.");

        // Step 2: Calculate
        double subtotal     = calculateSubtotal(prices);
        double discount     = calculateDiscount(subtotal, membershipLevel);
        double afterDiscount = subtotal - discount;
        double tax          = calculateTax(afterDiscount);
        double total        = afterDiscount + tax;

        // Step 3: Output
        System.out.println("  Subtotal: $" + String.format("%.2f", subtotal));
        System.out.println("  Discount (" + membershipLevel + "): -$"
                + String.format("%.2f", discount));
        System.out.println("  Tax (8%): $" + String.format("%.2f", tax));
        printReceipt(customerName, items, prices, subtotal, discount, tax, total);
    }

    /**
     * Demonstrates the Long Method refactoring.
     */
    public static void demo() {
        System.out.println("  [Long Method - AFTER refactoring]");
        System.out.println("  Logic extracted into small, focused methods:");
        System.out.println();

        String[] items = {"Laptop", "Mouse", "Keyboard"};
        double[] prices = {999.99, 29.99, 79.99};

        processOrder("Alice Johnson", items, prices, "GOLD");
    }
}
