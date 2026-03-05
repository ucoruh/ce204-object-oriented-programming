package com.example.week13.organizing;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Replace Type Code with Class
 * =============================================================================
 *
 * Intent:
 *   Replace a primitive type code (int or String constant) with a proper
 *   class (or enum in Java) that provides type safety.
 *
 * When to use:
 *   - A class uses int or String constants to represent categories/types.
 *   - The type code does not affect behavior (if it does, use State/Strategy
 *     or Replace Type Code with Subclasses instead).
 *   - You want the compiler to prevent invalid type values.
 *
 * In Java, this often means replacing int constants with an enum.
 *
 * This demo shows a before/after comparison.
 * =============================================================================
 */
public class ReplaceTypeCodeWithClassDemo {

    // =========================================================================
    // BEFORE: Type code as int constants
    // =========================================================================

    /**
     * BEFORE: Blood type represented as int constants.
     * Nothing prevents you from passing an invalid value like 99.
     */
    static class PatientBefore {
        // Type codes -- just ints with no safety
        static final int BLOOD_O = 0;
        static final int BLOOD_A = 1;
        static final int BLOOD_B = 2;
        static final int BLOOD_AB = 3;

        private String name;
        private int bloodType;  // could be ANY int -- dangerous

        PatientBefore(String name, int bloodType) {
            this.name = name;
            this.bloodType = bloodType;
        }

        String getBloodTypeLabel() {
            return switch (bloodType) {
                case BLOOD_O  -> "O";
                case BLOOD_A  -> "A";
                case BLOOD_B  -> "B";
                case BLOOD_AB -> "AB";
                default       -> "UNKNOWN (" + bloodType + ")";
            };
        }

        void print() {
            System.out.println("      Patient: " + name
                    + ", Blood Type: " + getBloodTypeLabel());
        }
    }

    // =========================================================================
    // AFTER: Type code replaced with enum
    // =========================================================================

    /**
     * Enum provides compile-time type safety and self-documentation.
     */
    enum BloodType {
        O("O"), A("A"), B("B"), AB("AB");

        private final String label;

        BloodType(String label) { this.label = label; }

        public String getLabel() { return label; }
    }

    /**
     * AFTER: Patient uses BloodType enum -- the compiler enforces valid values.
     */
    static class PatientAfter {
        private String name;
        private BloodType bloodType;  // Can only be O, A, B, or AB

        PatientAfter(String name, BloodType bloodType) {
            this.name = name;
            this.bloodType = bloodType;
        }

        void print() {
            System.out.println("      Patient: " + name
                    + ", Blood Type: " + bloodType.getLabel());
        }
    }

    /**
     * Demonstrates Replace Type Code with Class/Enum refactoring.
     */
    public static void demo() {
        System.out.println("  [Replace Type Code with Class - Before and After]");
        System.out.println();

        // Before: int codes
        System.out.println("    BEFORE (int type codes):");
        PatientBefore b1 = new PatientBefore("Alice", PatientBefore.BLOOD_A);
        b1.print();

        // Danger: nothing prevents invalid values
        PatientBefore bad = new PatientBefore("Bob", 99);
        bad.print();
        System.out.println("      (99 is not a valid blood type -- no compile error!)");

        System.out.println();

        // After: enum
        System.out.println("    AFTER (enum type):");
        PatientAfter a1 = new PatientAfter("Alice", BloodType.A);
        a1.print();

        // The following line would NOT compile:
        // PatientAfter bad2 = new PatientAfter("Bob", 99);  // ERROR!
        System.out.println("      Invalid values are now a compile-time error.");

        System.out.println();
        System.out.println("    Enums provide type safety, autocompletion, and");
        System.out.println("    can carry behavior (methods on enum constants).");
    }
}
