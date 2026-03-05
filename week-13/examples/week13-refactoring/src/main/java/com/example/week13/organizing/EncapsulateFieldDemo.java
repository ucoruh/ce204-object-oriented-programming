package com.example.week13.organizing;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Encapsulate Field
 * =============================================================================
 *
 * Intent:
 *   Make a public field private and provide getter/setter methods.
 *
 * When to use:
 *   - A class has a public field that is accessed directly by other classes.
 *   - You want to add validation, logging, or lazy initialization later.
 *   - You want to control how the field is read or modified.
 *
 * Mechanics:
 *   1. Create getter and setter methods for the field.
 *   2. Find all external references to the field.
 *   3. Replace direct reads with the getter, direct writes with the setter.
 *   4. Make the field private.
 *
 * This demo shows a before/after comparison with validation added.
 * =============================================================================
 */
public class EncapsulateFieldDemo {

    // =========================================================================
    // BEFORE: Public fields -- no control over access
    // =========================================================================

    /**
     * BEFORE: Fields are public -- anyone can set invalid values.
     */
    static class StudentBefore {
        public String name;    // No control
        public int age;        // Could be set to -5 -- no protection
        public double gpa;     // Could be set to 99.0 -- no validation

        StudentBefore(String name, int age, double gpa) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }

        void print() {
            System.out.println("      Name: " + name + ", Age: " + age
                    + ", GPA: " + gpa);
        }
    }

    // =========================================================================
    // AFTER: Private fields with getters/setters and validation
    // =========================================================================

    /**
     * AFTER: Fields are private. Setters enforce business rules.
     */
    static class StudentAfter {
        private String name;
        private int age;
        private double gpa;

        StudentAfter(String name, int age, double gpa) {
            setName(name);   // use setters in constructor for validation
            setAge(age);
            setGpa(gpa);
        }

        // --- Getters ---
        public String getName() { return name; }
        public int getAge() { return age; }
        public double getGpa() { return gpa; }

        // --- Setters with validation ---
        public void setName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty.");
            }
            this.name = name;
        }

        public void setAge(int age) {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException(
                        "Age must be between 0 and 150, got: " + age);
            }
            this.age = age;
        }

        public void setGpa(double gpa) {
            if (gpa < 0.0 || gpa > 4.0) {
                throw new IllegalArgumentException(
                        "GPA must be between 0.0 and 4.0, got: " + gpa);
            }
            this.gpa = gpa;
        }

        void print() {
            System.out.println("      Name: " + name + ", Age: " + age
                    + ", GPA: " + String.format("%.2f", gpa));
        }
    }

    /**
     * Demonstrates Encapsulate Field refactoring.
     */
    public static void demo() {
        System.out.println("  [Encapsulate Field - Before and After]");
        System.out.println();

        // Before: no protection
        System.out.println("    BEFORE (public fields, no validation):");
        StudentBefore before = new StudentBefore("Eve", 20, 3.75);
        before.print();
        before.age = -5;    // BAD: no validation, this succeeds silently
        before.gpa = 99.0;  // BAD: no validation
        System.out.println("    After setting invalid values:");
        before.print();

        System.out.println();

        // After: validation in setters
        System.out.println("    AFTER (private fields, validated setters):");
        StudentAfter after = new StudentAfter("Eve", 20, 3.75);
        after.print();

        // Trying to set invalid values now throws exceptions
        System.out.println("    Attempting to set age = -5:");
        try {
            after.setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("      Caught: " + e.getMessage());
        }

        System.out.println("    Attempting to set GPA = 99.0:");
        try {
            after.setGpa(99.0);
        } catch (IllegalArgumentException e) {
            System.out.println("      Caught: " + e.getMessage());
        }

        System.out.println("    Values remain valid:");
        after.print();
        System.out.println();
        System.out.println("    Encapsulation lets us enforce invariants.");
    }
}
