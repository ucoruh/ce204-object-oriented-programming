package com.example.week12.bloaters;

/**
 * =============================================================================
 * CODE SMELL FIX: Primitive Obsession (AFTER Refactoring)
 * =============================================================================
 *
 * Solution Applied: Replace Data Value with Object (Value Objects)
 *
 *   Domain concepts now have their own types:
 *     - Money       -- encapsulates amount + currency with formatting
 *     - PhoneNumber -- encapsulates and validates phone number strings
 *
 * Benefits:
 *   - Type safety: you cannot accidentally swap a Money with a PhoneNumber.
 *   - Validation happens once, in the constructor of the value object.
 *   - Formatting and business logic live inside the value object.
 *   - Self-documenting: method signatures clearly show what is expected.
 * =============================================================================
 */
public class PrimitiveObsessionAfter {

    // =========================================================================
    // Value Object: Money
    // =========================================================================

    /**
     * Encapsulates a monetary amount with its currency.
     * Validation and formatting live here, not scattered in client code.
     */
    static class Money {
        private final double amount;
        private final String currency;

        public Money(double amount, String currency) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative.");
            }
            if (currency == null || !currency.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException(
                        "Currency must be a 3-letter uppercase code.");
            }
            this.amount = amount;
            this.currency = currency;
        }

        /** Returns a formatted string like "$29.99" or "E49.99". */
        public String formatted() {
            String symbol = switch (currency) {
                case "USD" -> "$";
                case "EUR" -> "E";
                case "TRY" -> "TL";
                default    -> currency + " ";
            };
            return symbol + String.format("%.2f", amount);
        }

        public double getAmount() { return amount; }
        public String getCurrency() { return currency; }
    }

    // =========================================================================
    // Value Object: PhoneNumber
    // =========================================================================

    /**
     * Encapsulates a phone number with basic validation.
     */
    static class PhoneNumber {
        private final String number;

        public PhoneNumber(String number) {
            if (number == null || !number.matches("\\+?[\\d\\-]{7,15}")) {
                throw new IllegalArgumentException(
                        "Invalid phone number: " + number);
            }
            this.number = number;
        }

        @Override
        public String toString() {
            return number;
        }
    }

    // =========================================================================
    // Refactored Product using value objects
    // =========================================================================

    /**
     * GOOD EXAMPLE: Product now uses value objects.  The compiler
     * prevents accidental swaps, and each value validates itself.
     */
    static class Product {
        private final String name;
        private final Money price;
        private final PhoneNumber supportPhone;

        Product(String name, Money price, PhoneNumber supportPhone) {
            this.name = name;
            this.price = price;
            this.supportPhone = supportPhone;
        }

        void printInfo() {
            System.out.println("    Product: " + name);
            System.out.println("    Price:   " + price.formatted());
            System.out.println("    Support: " + supportPhone);
        }
    }

    /**
     * Demonstrates the Primitive Obsession refactoring.
     */
    public static void demo() {
        System.out.println("  [Primitive Obsession - AFTER refactoring]");
        System.out.println("  Domain concepts wrapped in value objects:");
        System.out.println();

        // Clear, type-safe construction
        Money price = new Money(29.99, "USD");
        PhoneNumber phone = new PhoneNumber("+1-800-555-0199");
        Product p = new Product("Wireless Mouse", price, phone);
        p.printInfo();

        System.out.println();
        System.out.println("    [Swapping Money and PhoneNumber now causes a");
        System.out.println("     compile error -- the type system protects us!]");

        // The following line would NOT compile:
        // Product broken = new Product("Keyboard", phone, price);  // ERROR!
    }
}
