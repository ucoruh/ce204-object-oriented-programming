package com.example.week13.composing;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Inline Method
 * =============================================================================
 *
 * Intent:
 *   Put the method's body into the body of its callers and remove the method.
 *   This is the inverse of Extract Method.
 *
 * When to use:
 *   - A method's body is as clear as its name (the indirection adds no value).
 *   - Too many small methods make the code harder to follow (over-extraction).
 *   - The method is called in only one place and its name does not add clarity.
 *
 * Caution:
 *   - Do NOT inline a method that is called from multiple places if each
 *     call would duplicate non-trivial logic.
 *   - Do NOT inline if the method encapsulates important domain semantics.
 *
 * This demo shows when Inline Method improves readability.
 * =============================================================================
 */
public class InlineMethodDemo {

    // =========================================================================
    // BEFORE: Over-extracted -- trivial helpers add indirection, not clarity
    // =========================================================================

    /**
     * BEFORE: These tiny methods just wrap obvious one-liners.
     * The indirection makes the code HARDER to read, not easier.
     */
    static class RatingCalculatorBefore {
        private int points;

        RatingCalculatorBefore(int points) { this.points = points; }

        // Trivial method that adds no clarity
        private boolean moreThanFivePoints() {
            return points > 5;
        }

        // Another trivial wrapper
        private boolean moreThanTenPoints() {
            return points > 10;
        }

        String getRating() {
            if (moreThanTenPoints()) {     // reader must look up method
                return "EXCELLENT";
            } else if (moreThanFivePoints()) { // reader must look up method
                return "GOOD";
            } else {
                return "AVERAGE";
            }
        }
    }

    // =========================================================================
    // AFTER: Trivial methods inlined -- reads clearly without indirection
    // =========================================================================

    /**
     * AFTER: Conditions are inlined since they are perfectly clear.
     */
    static class RatingCalculatorAfter {
        private int points;

        RatingCalculatorAfter(int points) { this.points = points; }

        String getRating() {
            // Inlined: the conditions are self-explanatory
            if (points > 10) {
                return "EXCELLENT";
            } else if (points > 5) {
                return "GOOD";
            } else {
                return "AVERAGE";
            }
        }
    }

    /**
     * Demonstrates Inline Method refactoring.
     */
    public static void demo() {
        System.out.println("  [Inline Method - Before and After]");
        System.out.println();

        int[] testPoints = {3, 7, 12};

        System.out.println("    BEFORE (over-extracted trivial helpers):");
        for (int p : testPoints) {
            RatingCalculatorBefore before = new RatingCalculatorBefore(p);
            System.out.println("      points=" + p + " -> " + before.getRating());
        }

        System.out.println();
        System.out.println("    AFTER (trivial methods inlined):");
        for (int p : testPoints) {
            RatingCalculatorAfter after = new RatingCalculatorAfter(p);
            System.out.println("      points=" + p + " -> " + after.getRating());
        }

        System.out.println();
        System.out.println("    Same behavior, but AFTER version is easier to");
        System.out.println("    follow because trivial indirection is removed.");
    }
}
