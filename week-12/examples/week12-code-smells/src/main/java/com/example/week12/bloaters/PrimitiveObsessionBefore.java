package com.example.week12.bloaters;

/**
 * =============================================================================
 * CODE SMELL: Primitive Obsession (BEFORE Refactoring)
 * =============================================================================
 *
 * Problem:
 *   Using primitive types (String, int, double) to represent domain concepts
 *   that deserve their own classes.  For example, representing money as a
 *   bare double, a phone number as a plain String, or a temperature with
 *   separate value and unit fields.
 *
 * Smell Category: Bloater
 *
 * Why it is bad:
 *   - No type safety: a phone-number String can be assigned to a name String.
 *   - Validation logic is duplicated wherever the value is used.
 *   - Business rules (e.g., "money must not be negative") have no home.
 *   - Related operations (e.g., currency conversion) are scattered.
 *
 * Refactoring: Replace Data Value with Object -- see PrimitiveObsessionAfter.java
 * =============================================================================
 */
public class PrimitiveObsessionBefore {

    /**
     * BAD EXAMPLE: A product represented entirely with primitives.
     * There is no type safety and no central place for validation.
     */
    static class Product {
        String name;
        double price;          // What currency? No way to tell.
        String currency;       // Just a string -- easy to mis-type "USd"
        String phoneSupport;   // Phone number as raw string -- no validation

        Product(String name, double price, String currency,
                String phoneSupport) {
            this.name = name;
            this.price = price;
            this.currency = currency;
            this.phoneSupport = phoneSupport;
        }

        void printInfo() {
            // Validation scattered: must check currency format every time
            String currencySymbol;
            if (currency.equals("USD")) {
                currencySymbol = "$";
            } else if (currency.equals("EUR")) {
                currencySymbol = "E";
            } else if (currency.equals("TRY")) {
                currencySymbol = "TL";
            } else {
                currencySymbol = currency;
            }

            System.out.println("    Product: " + name);
            System.out.println("    Price:   " + currencySymbol
                    + String.format("%.2f", price));
            System.out.println("    Support: " + phoneSupport);
        }
    }

    /**
     * Demonstrates the Primitive Obsession code smell.
     */
    public static void demo() {
        System.out.println("  [Primitive Obsession - BEFORE refactoring]");
        System.out.println("  Domain concepts represented as bare primitives:");
        System.out.println();

        // No type safety: could easily swap currency and phone values
        Product p = new Product("Wireless Mouse", 29.99, "USD",
                "+1-800-555-0199");
        p.printInfo();

        // This compiles fine but is logically wrong -- no protection!
        Product broken = new Product("Keyboard", 49.99,
                "+1-800-555-0199",   // Oops! Phone number as currency
                "EUR");              // Oops! Currency as phone number
        System.out.println();
        System.out.println("    [Swapped values compile fine -- no type safety!]");
        broken.printInfo();
    }
}
