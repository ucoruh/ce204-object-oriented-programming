package com.example.week13.conditionals;

import java.time.LocalDate;
import java.time.Month;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Decompose Conditional
 * =============================================================================
 *
 * Intent:
 *   Extract the condition, the then-part, and the else-part into separate
 *   methods with intention-revealing names.
 *
 * When to use:
 *   - A complex conditional (if-then-else) is hard to understand.
 *   - The condition expression is long or involves multiple sub-conditions.
 *   - The then/else bodies contain non-trivial logic.
 *
 * Mechanics:
 *   1. Extract the condition into a method with a descriptive name.
 *   2. Extract the then-clause into a method.
 *   3. Extract the else-clause into a method.
 *   4. The if-then-else now reads like English.
 *
 * This demo shows a pricing rule before/after decomposition.
 * =============================================================================
 */
public class DecomposeConditionalDemo {

    // =========================================================================
    // BEFORE: Complex conditional with opaque logic
    // =========================================================================

    /**
     * BEFORE: The condition and branches are hard to understand at a glance.
     */
    static double calculatePriceBefore(LocalDate date, double basePrice,
                                        double quantity) {
        double charge;
        // What does this condition mean?  Reader must decode it.
        if (date.getMonth().getValue() >= 6 && date.getMonth().getValue() <= 8) {
            charge = quantity * (basePrice * 1.20);  // Why 1.20?
        } else {
            charge = quantity * (basePrice * 0.90);  // Why 0.90?
        }
        return charge;
    }

    // =========================================================================
    // AFTER: Condition and branches decomposed into named methods
    // =========================================================================

    /** Extracted condition: is it summer (June-August)? */
    private static boolean isSummer(LocalDate date) {
        int month = date.getMonth().getValue();
        return month >= 6 && month <= 8;
    }

    /** Extracted then-clause: summer surcharge of 20%. */
    private static double summerCharge(double basePrice, double quantity) {
        return quantity * (basePrice * 1.20);
    }

    /** Extracted else-clause: off-season discount of 10%. */
    private static double offSeasonCharge(double basePrice, double quantity) {
        return quantity * (basePrice * 0.90);
    }

    /**
     * AFTER: Reads like a sentence: "if summer then summer charge,
     * otherwise off-season charge."
     */
    static double calculatePriceAfter(LocalDate date, double basePrice,
                                       double quantity) {
        if (isSummer(date)) {
            return summerCharge(basePrice, quantity);
        } else {
            return offSeasonCharge(basePrice, quantity);
        }
    }

    /**
     * Demonstrates Decompose Conditional refactoring.
     */
    public static void demo() {
        System.out.println("  [Decompose Conditional - Before and After]");
        System.out.println();

        LocalDate summer = LocalDate.of(2025, Month.JULY, 15);
        LocalDate winter = LocalDate.of(2025, Month.JANUARY, 10);
        double basePrice = 100.0;
        double quantity = 5;

        // Before
        System.out.println("    BEFORE (opaque conditional):");
        System.out.println("      Summer (Jul 15): $"
                + String.format("%.2f",
                    calculatePriceBefore(summer, basePrice, quantity)));
        System.out.println("      Winter (Jan 10): $"
                + String.format("%.2f",
                    calculatePriceBefore(winter, basePrice, quantity)));

        System.out.println();

        // After
        System.out.println("    AFTER (decomposed conditional):");
        System.out.println("      Summer (Jul 15): $"
                + String.format("%.2f",
                    calculatePriceAfter(summer, basePrice, quantity)));
        System.out.println("      Winter (Jan 10): $"
                + String.format("%.2f",
                    calculatePriceAfter(winter, basePrice, quantity)));

        System.out.println();
        System.out.println("    Same result, but AFTER version reveals intent:");
        System.out.println("    isSummer() -> summerCharge() / offSeasonCharge()");
    }
}
