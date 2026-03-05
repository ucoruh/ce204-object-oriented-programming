package com.example.week13.generalization;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Pull Up Method
 * =============================================================================
 *
 * Intent:
 *   Move identical methods from subclasses up into their common superclass
 *   to eliminate duplication.
 *
 * When to use:
 *   - Two or more subclasses have methods with identical (or very similar)
 *     bodies.
 *   - The method does not depend on subclass-specific state.
 *   - You want to enforce consistency -- one implementation, not N copies.
 *
 * Mechanics:
 *   1. Inspect the candidate methods in each subclass for identical bodies.
 *   2. If they reference subclass-specific features, first apply other
 *      refactorings (e.g., Pull Up Field) to make the bodies identical.
 *   3. Copy the method body to the superclass.
 *   4. Remove the method from all subclasses.
 *   5. Compile and test.
 *
 * This demo shows a before/after comparison.
 * =============================================================================
 */
public class PullUpMethodDemo {

    // =========================================================================
    // BEFORE: Identical toString() duplicated in every subclass
    // =========================================================================

    /**
     * BEFORE: Base class without the common method.
     */
    static abstract class EmployeeBefore {
        protected String name;
        protected String department;

        EmployeeBefore(String name, String department) {
            this.name = name;
            this.department = department;
        }

        abstract double annualCost();
    }

    static class FullTimeEmployeeBefore extends EmployeeBefore {
        private double monthlySalary;

        FullTimeEmployeeBefore(String name, String dept, double salary) {
            super(name, dept);
            this.monthlySalary = salary;
        }

        @Override double annualCost() { return monthlySalary * 12; }

        // DUPLICATED method -- same logic as PartTimeEmployeeBefore
        String getSummary() {
            return name + " (" + department + ") - Annual: $"
                    + String.format("%.2f", annualCost());
        }
    }

    static class PartTimeEmployeeBefore extends EmployeeBefore {
        private double hourlyRate;
        private int hoursPerYear;

        PartTimeEmployeeBefore(String name, String dept, double rate, int hours) {
            super(name, dept);
            this.hourlyRate = rate;
            this.hoursPerYear = hours;
        }

        @Override double annualCost() { return hourlyRate * hoursPerYear; }

        // DUPLICATED method -- same logic as FullTimeEmployeeBefore
        String getSummary() {
            return name + " (" + department + ") - Annual: $"
                    + String.format("%.2f", annualCost());
        }
    }

    // =========================================================================
    // AFTER: getSummary() pulled up into the superclass
    // =========================================================================

    /**
     * AFTER: Common method lives in the base class -- one copy.
     */
    static abstract class EmployeeAfter {
        protected String name;
        protected String department;

        EmployeeAfter(String name, String department) {
            this.name = name;
            this.department = department;
        }

        abstract double annualCost();

        /** Pulled-up method: exists once, used by all subclasses. */
        String getSummary() {
            return name + " (" + department + ") - Annual: $"
                    + String.format("%.2f", annualCost());
        }
    }

    static class FullTimeEmployeeAfter extends EmployeeAfter {
        private double monthlySalary;

        FullTimeEmployeeAfter(String name, String dept, double salary) {
            super(name, dept);
            this.monthlySalary = salary;
        }

        @Override double annualCost() { return monthlySalary * 12; }
        // getSummary() is inherited -- no duplication
    }

    static class PartTimeEmployeeAfter extends EmployeeAfter {
        private double hourlyRate;
        private int hoursPerYear;

        PartTimeEmployeeAfter(String name, String dept, double rate, int hours) {
            super(name, dept);
            this.hourlyRate = rate;
            this.hoursPerYear = hours;
        }

        @Override double annualCost() { return hourlyRate * hoursPerYear; }
        // getSummary() is inherited -- no duplication
    }

    /**
     * Demonstrates Pull Up Method refactoring.
     */
    public static void demo() {
        System.out.println("  [Pull Up Method - Before and After]");
        System.out.println();

        // Before
        System.out.println("    BEFORE (getSummary duplicated in each subclass):");
        FullTimeEmployeeBefore ft = new FullTimeEmployeeBefore(
                "Alice", "Engineering", 8000);
        PartTimeEmployeeBefore pt = new PartTimeEmployeeBefore(
                "Bob", "Support", 25, 1200);
        System.out.println("      " + ft.getSummary());
        System.out.println("      " + pt.getSummary());

        System.out.println();

        // After
        System.out.println("    AFTER (getSummary pulled up to base class):");
        FullTimeEmployeeAfter ft2 = new FullTimeEmployeeAfter(
                "Alice", "Engineering", 8000);
        PartTimeEmployeeAfter pt2 = new PartTimeEmployeeAfter(
                "Bob", "Support", 25, 1200);
        System.out.println("      " + ft2.getSummary());
        System.out.println("      " + pt2.getSummary());

        System.out.println();
        System.out.println("    One method in the base class replaces N identical");
        System.out.println("    copies in the subclasses. A fix applies everywhere.");
    }
}
