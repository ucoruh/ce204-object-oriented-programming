package com.example.week12.couplers;

/**
 * =============================================================================
 * CODE SMELL FIX: Feature Envy (AFTER Refactoring)
 * =============================================================================
 *
 * Solution Applied: Move Method
 *
 *   The compensation calculation now lives inside Employee, where the data
 *   lives.  PayrollReport delegates to Employee instead of reaching in.
 *
 * Benefits:
 *   - Employee encapsulates its own salary logic -- data and behavior together.
 *   - PayrollReport is decoupled from Employee's internal field structure.
 *   - If Employee fields change, only Employee methods need updating.
 *   - The compensation logic can be reused without PayrollReport.
 * =============================================================================
 */
public class FeatureEnvyAfter {

    // =========================================================================
    // Employee now owns its own compensation logic
    // =========================================================================

    /**
     * GOOD EXAMPLE: The calculation method lives where the data lives.
     */
    static class Employee {
        private final String name;
        private final double baseSalary;
        private final int yearsOfService;
        private final String department;
        private final double performanceRating;

        Employee(String name, double baseSalary, int yearsOfService,
                 String department, double performanceRating) {
            this.name = name;
            this.baseSalary = baseSalary;
            this.yearsOfService = yearsOfService;
            this.department = department;
            this.performanceRating = performanceRating;
        }

        public String getName() { return name; }
        public double getBaseSalary() { return baseSalary; }

        /** Seniority bonus: 2% per year of service. */
        private double seniorityBonus() {
            return baseSalary * (yearsOfService * 0.02);
        }

        /** Performance bonus based on rating thresholds. */
        private double performanceBonus() {
            if (performanceRating >= 4.5) return baseSalary * 0.20;
            if (performanceRating >= 3.5) return baseSalary * 0.10;
            return 0;
        }

        /** Department-specific salary adjustment. */
        private double departmentAdjustment() {
            return switch (department) {
                case "Engineering" -> baseSalary * 0.05;
                case "Sales"       -> baseSalary * 0.03;
                default            -> 0;
            };
        }

        /**
         * Now the compensation logic lives with the data it operates on.
         * No external class needs to reach into our fields.
         */
        public double totalCompensation() {
            return baseSalary + seniorityBonus() + performanceBonus()
                    + departmentAdjustment();
        }
    }

    // =========================================================================
    // PayrollReport is now a thin presenter
    // =========================================================================

    /**
     * GOOD: PayrollReport only asks Employee for results -- it does not
     * dig into Employee's fields to compute things itself.
     */
    static class PayrollReport {
        static void printReport(Employee emp) {
            System.out.println("    Employee: " + emp.getName());
            System.out.println("    Base:     $"
                    + String.format("%.2f", emp.getBaseSalary()));
            System.out.println("    Total:    $"
                    + String.format("%.2f", emp.totalCompensation()));
        }
    }

    /**
     * Demonstrates the Feature Envy refactoring.
     */
    public static void demo() {
        System.out.println("  [Feature Envy - AFTER refactoring]");
        System.out.println("  Compensation logic moved to Employee:");
        System.out.println();

        Employee emp = new Employee("Carol", 80000, 5,
                "Engineering", 4.7);
        PayrollReport.printReport(emp);
        System.out.println();
        System.out.println("    Now PayrollReport only calls emp.totalCompensation()");
        System.out.println("    -- it does not access any internal fields.");
    }
}
