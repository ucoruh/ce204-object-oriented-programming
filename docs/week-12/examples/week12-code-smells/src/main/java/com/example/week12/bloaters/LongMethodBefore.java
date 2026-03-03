package com.example.week12.bloaters;

/**
 * =============================================================================
 * CODE SMELL: Long Method (BEFORE Refactoring)
 * =============================================================================
 *
 * Problem:
 *   A method that tries to do too many things at once. It is hard to read,
 *   hard to test, and hard to reuse individual pieces of its logic.
 *
 * Smell Category: Bloater
 *
 * Why it is bad:
 *   - Violates the Single Responsibility Principle at the method level.
 *   - Difficult to understand the overall flow at a glance.
 *   - Impossible to reuse sub-steps independently.
 *   - Testing requires exercising the entire method every time.
 *
 * Refactoring: Extract Method -- see LongMethodAfter.java
 * =============================================================================
 */
public class LongMethodBefore {

    /**
     * BAD EXAMPLE: This single method handles order validation, discount
     * calculation, tax computation, and receipt printing -- all in one place.
     * In real code this might be hundreds of lines long.
     */
    public static void processOrder(String customerName, String[] items,
                                     double[] prices, String membershipLevel) {
        // ---- Step 1: Validate the order ----
        if (customerName == null || customerName.isEmpty()) {
            System.out.println("  [ERROR] Customer name is required.");
            return;
        }
        if (items == null || items.length == 0) {
            System.out.println("  [ERROR] No items in the order.");
            return;
        }
        if (items.length != prices.length) {
            System.out.println("  [ERROR] Items and prices arrays must match.");
            return;
        }
        for (double price : prices) {
            if (price < 0) {
                System.out.println("  [ERROR] Prices cannot be negative.");
                return;
            }
        }
        System.out.println("  Order validated successfully.");

        // ---- Step 2: Calculate subtotal ----
        double subtotal = 0;
        for (double price : prices) {
            subtotal += price;
        }
        System.out.println("  Subtotal: $" + String.format("%.2f", subtotal));

        // ---- Step 3: Apply discount based on membership ----
        double discount = 0;
        if (membershipLevel != null) {
            if (membershipLevel.equals("GOLD")) {
                discount = subtotal * 0.15;
            } else if (membershipLevel.equals("SILVER")) {
                discount = subtotal * 0.10;
            } else if (membershipLevel.equals("BRONZE")) {
                discount = subtotal * 0.05;
            }
        }
        double afterDiscount = subtotal - discount;
        System.out.println("  Discount (" + membershipLevel + "): -$"
                + String.format("%.2f", discount));

        // ---- Step 4: Calculate tax ----
        double taxRate = 0.08;
        double tax = afterDiscount * taxRate;
        double total = afterDiscount + tax;
        System.out.println("  Tax (8%): $" + String.format("%.2f", tax));

        // ---- Step 5: Print receipt ----
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

    /**
     * Demonstrates the Long Method code smell.
     */
    public static void demo() {
        System.out.println("  [Long Method - BEFORE refactoring]");
        System.out.println("  All logic crammed into one big method:");
        System.out.println();

        String[] items = {"Laptop", "Mouse", "Keyboard"};
        double[] prices = {999.99, 29.99, 79.99};

        processOrder("Alice Johnson", items, prices, "GOLD");
    }
}
