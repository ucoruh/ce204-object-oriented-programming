package com.example.week12.dispensables;

/**
 * =============================================================================
 * CODE SMELL: Dead Code
 * =============================================================================
 *
 * Problem:
 *   Code that is never executed, never called, or has no effect.  This
 *   includes: unreachable methods, unused variables, commented-out blocks,
 *   and conditions that can never be true.
 *
 * Smell Category: Dispensable
 *
 * Why it is bad:
 *   - Clutters the codebase and distracts readers.
 *   - May mislead developers into thinking it serves a purpose.
 *   - Increases maintenance burden during refactoring and reviews.
 *   - Can mask real bugs if dead paths hide incorrect logic.
 *
 * Refactoring: Remove Dead Code -- delete it.  Version control keeps history.
 *
 * This demo shows examples of dead code and explains what to remove.
 * =============================================================================
 */
public class DeadCodeDemo {

    // =========================================================================
    // Example 1: Unused private method (Dead Code)
    // =========================================================================

    /**
     * DEAD CODE: This method is never called anywhere.
     * In a real codebase you would simply delete it.
     */
    @SuppressWarnings("unused")
    private static String formatLegacyReport(String data) {
        // This was used in version 1.0 but replaced by formatReport().
        // It should have been deleted, not left here "just in case."
        return "[LEGACY] " + data;
    }

    /** The active method that replaced the legacy one. */
    private static String formatReport(String data) {
        return "[REPORT] " + data;
    }

    // =========================================================================
    // Example 2: Unreachable code after return
    // =========================================================================

    /**
     * DEAD CODE: The "Unreachable" print statement can never execute.
     */
    private static int calculateScore(int points) {
        if (points > 0) {
            return points * 10;
        } else {
            return 0;
        }
        // Dead code below: the compiler may warn about this.
        // System.out.println("Unreachable -- this never runs!");
    }

    // =========================================================================
    // Example 3: Unused variables
    // =========================================================================

    /**
     * DEAD CODE: Several variables are computed but never used.
     * They waste CPU cycles and confuse the reader.
     */
    private static void processData(String input) {
        @SuppressWarnings("unused")
        int unusedLength = input.length();   // computed but never read

        @SuppressWarnings("unused")
        String unusedUpper = input.toUpperCase(); // computed but never read

        // Only this variable is actually used:
        String trimmed = input.trim();
        System.out.println("    Processed: \"" + trimmed + "\"");
    }

    // =========================================================================
    // Example 4: Condition that is always false
    // =========================================================================

    /**
     * DEAD CODE: The condition (value < 0) is dead because we already
     * guaranteed value >= 0 at the call site through Math.abs().
     */
    private static String classify(int value) {
        // 'value' is always >= 0 due to Math.abs() at call site
        if (value > 100) {
            return "HIGH";
        } else if (value > 50) {
            return "MEDIUM";
        } else if (value < 0) {
            // This branch can NEVER execute -- dead code
            return "NEGATIVE (dead branch!)";
        } else {
            return "LOW";
        }
    }

    /**
     * Demonstrates dead code identification.
     */
    public static void demo() {
        System.out.println("  [Dead Code - Identification and Removal]");
        System.out.println();

        // Example 1: unused method
        System.out.println("  Example 1: Unused private method");
        System.out.println("    formatLegacyReport() exists but is never called.");
        System.out.println("    -> Solution: DELETE it. Git keeps history.");
        System.out.println("    Active method output: " + formatReport("Sales Q4"));
        System.out.println();

        // Example 2: unreachable code
        System.out.println("  Example 2: Unreachable code after return");
        System.out.println("    calculateScore() has code after all return paths.");
        System.out.println("    -> Solution: DELETE unreachable statements.");
        System.out.println("    Score: " + calculateScore(8));
        System.out.println();

        // Example 3: unused variables
        System.out.println("  Example 3: Unused variables");
        System.out.println("    processData() computes values it never reads.");
        System.out.println("    -> Solution: REMOVE unused variable declarations.");
        processData("  Hello World  ");
        System.out.println();

        // Example 4: impossible condition
        System.out.println("  Example 4: Impossible condition branch");
        int absValue = Math.abs(-42);
        System.out.println("    classify(" + absValue + ") = " + classify(absValue));
        System.out.println("    The (value < 0) branch can never execute.");
        System.out.println("    -> Solution: REMOVE the dead branch.");
        System.out.println();

        System.out.println("  KEY RULE: If code is not called, not reachable, or");
        System.out.println("  has no effect -- delete it. Version control is your");
        System.out.println("  safety net, not commented-out code.");
    }
}
