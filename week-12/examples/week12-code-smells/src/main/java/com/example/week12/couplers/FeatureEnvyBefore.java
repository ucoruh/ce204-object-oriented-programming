package com.example.week12.couplers;

/**
 * =============================================================================
 * CODE SMELL: Feature Envy (BEFORE Refactoring)
 * =============================================================================
 *
 * Problem:
 *   A method in one class is more interested in the data of another class
 *   than in the data of its own class.  The method "envies" the features
 *   of the other class and constantly reaches into it for data.
 *
 * Smell Category: Coupler
 *
 * Why it is bad:
 *   - Tight coupling: the envious class depends heavily on the internal
 *     structure of the other class.
 *   - If the data class changes its fields, the envious class breaks.
 *   - The logic is in the wrong place -- it should live where the data lives.
 *   - Hard to reuse the calculation without dragging in the envious class.
 *
 * Refactoring: Move Method -- see FeatureEnvyAfter.java
 * =============================================================================
 */
public class FeatureEnvyBefore {

    /**
     * Simple data holder for an employee.
     */
    static class Employee {
        String name;
        double baseSalary;
        int yearsOfService;
        String department;
        double performanceRating; // 1.0 to 5.0

        Employee(String name, double baseSalary, int yearsOfService,
                 String department, double performanceRating) {
            this.name = name;
            this.baseSalary = baseSalary;
            this.yearsOfService = yearsOfService;
            this.department = department;
            this.performanceRating = performanceRating;
        }
    }

    /**
     * BAD EXAMPLE: PayrollReport is envious of Employee -- it constantly
     * reaches into Employee's fields to perform calculations that logically
     * belong to Employee itself.
     */
    static class PayrollReport {

        /**
         * This method accesses almost every field of Employee.
         * It should be a method ON Employee, not on PayrollReport.
         */
        static double calculateTotalCompensation(Employee emp) {
            // Seniority bonus: 2% per year of service
            double seniorityBonus = emp.baseSalary * (emp.yearsOfService * 0.02);

            // Performance bonus: based on rating
            double performanceBonus;
            if (emp.performanceRating >= 4.5) {
                performanceBonus = emp.baseSalary * 0.20;
            } else if (emp.performanceRating >= 3.5) {
                performanceBonus = emp.baseSalary * 0.10;
            } else {
                performanceBonus = 0;
            }

            // Department adjustment
            double deptAdjustment = 0;
            if (emp.department.equals("Engineering")) {
                deptAdjustment = emp.baseSalary * 0.05;
            } else if (emp.department.equals("Sales")) {
                deptAdjustment = emp.baseSalary * 0.03;
            }

            return emp.baseSalary + seniorityBonus + performanceBonus
                    + deptAdjustment;
        }

        static void printReport(Employee emp) {
            double total = calculateTotalCompensation(emp);
            System.out.println("    Employee: " + emp.name);
            System.out.println("    Base:     $"
                    + String.format("%.2f", emp.baseSalary));
            System.out.println("    Total:    $"
                    + String.format("%.2f", total));
        }
    }

    /**
     * Demonstrates the Feature Envy code smell.
     */
    public static void demo() {
        System.out.println("  [Feature Envy - BEFORE refactoring]");
        System.out.println("  PayrollReport envies Employee data:");
        System.out.println();

        Employee emp = new Employee("Carol", 80000, 5,
                "Engineering", 4.7);
        PayrollReport.printReport(emp);
        System.out.println();
        System.out.println("    Problem: calculateTotalCompensation() uses 5 fields");
        System.out.println("    from Employee but lives in PayrollReport.");
    }
}
