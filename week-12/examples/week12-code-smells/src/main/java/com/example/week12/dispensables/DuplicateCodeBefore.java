package com.example.week12.dispensables;

/**
 * =============================================================================
 * CODE SMELL: Duplicate Code (BEFORE Refactoring)
 * =============================================================================
 *
 * Problem:
 *   The same (or very similar) code appears in multiple places.  When a
 *   bug is found or a change is needed, every copy must be updated --
 *   and it is easy to miss one.
 *
 * Smell Category: Dispensable
 *
 * Why it is bad:
 *   - Violates the DRY (Don't Repeat Yourself) principle.
 *   - Bug fixes must be applied in multiple places.
 *   - Increases codebase size without adding value.
 *   - Divergent copies lead to subtle, hard-to-trace inconsistencies.
 *
 * Refactoring: Extract Method / Extract Superclass
 *              -- see DuplicateCodeAfter.java
 * =============================================================================
 */
public class DuplicateCodeBefore {

    /**
     * BAD EXAMPLE: Student report and Employee report have nearly
     * identical formatting logic, duplicated in each method.
     */
    static class ReportGenerator {

        /** Generates a student grade report. */
        static void generateStudentReport(String name, String id,
                                           double gpa) {
            // ---- Header (DUPLICATED) ----
            System.out.println("    ====================================");
            System.out.println("    |        OFFICIAL REPORT           |");
            System.out.println("    ====================================");
            System.out.println("    Date: 2025-03-15");
            System.out.println("    ------------------------------------");

            // ---- Student-specific content ----
            System.out.println("    Student Name: " + name);
            System.out.println("    Student ID:   " + id);
            System.out.println("    GPA:          " + String.format("%.2f", gpa));
            String standing = gpa >= 3.0 ? "Good Standing" : "Probation";
            System.out.println("    Standing:     " + standing);

            // ---- Footer (DUPLICATED) ----
            System.out.println("    ------------------------------------");
            System.out.println("    This report is auto-generated.");
            System.out.println("    ====================================");
        }

        /** Generates an employee performance report. */
        static void generateEmployeeReport(String name, String empId,
                                            double rating) {
            // ---- Header (DUPLICATED -- same as student report!) ----
            System.out.println("    ====================================");
            System.out.println("    |        OFFICIAL REPORT           |");
            System.out.println("    ====================================");
            System.out.println("    Date: 2025-03-15");
            System.out.println("    ------------------------------------");

            // ---- Employee-specific content ----
            System.out.println("    Employee Name: " + name);
            System.out.println("    Employee ID:   " + empId);
            System.out.println("    Rating:        "
                    + String.format("%.1f", rating) + " / 5.0");
            String level = rating >= 4.0 ? "Exceeds" : "Meets";
            System.out.println("    Performance:   " + level);

            // ---- Footer (DUPLICATED -- same as student report!) ----
            System.out.println("    ------------------------------------");
            System.out.println("    This report is auto-generated.");
            System.out.println("    ====================================");
        }
    }

    /**
     * Demonstrates the Duplicate Code smell.
     */
    public static void demo() {
        System.out.println("  [Duplicate Code - BEFORE refactoring]");
        System.out.println("  Header/footer logic copy-pasted in both reports:");
        System.out.println();

        ReportGenerator.generateStudentReport("Eve", "S12345", 3.85);
        System.out.println();
        ReportGenerator.generateEmployeeReport("Frank", "E67890", 4.2);
        System.out.println();
        System.out.println("    Problem: The header and footer are identical");
        System.out.println("    but duplicated in each method.");
    }
}
