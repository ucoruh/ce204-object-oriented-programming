package com.example.week12.dispensables;

/**
 * =============================================================================
 * CODE SMELL FIX: Duplicate Code (AFTER Refactoring)
 * =============================================================================
 *
 * Solution Applied: Extract Method
 *
 *   The duplicated header and footer logic has been extracted into shared
 *   helper methods: printHeader() and printFooter().  Each report method
 *   now calls the shared helpers and only contains its unique content.
 *
 * Benefits:
 *   - DRY: header/footer logic exists in exactly one place.
 *   - A bug fix or format change is applied once and affects all reports.
 *   - Report methods are shorter and focused on their unique content.
 *   - Adding a new report type is straightforward -- reuse the helpers.
 * =============================================================================
 */
public class DuplicateCodeAfter {

    /**
     * GOOD EXAMPLE: Common formatting extracted into reusable methods.
     */
    static class ReportGenerator {

        // =====================================================================
        // Shared helpers -- the single source of truth for formatting
        // =====================================================================

        /** Prints the standard report header. */
        private static void printHeader() {
            System.out.println("    ====================================");
            System.out.println("    |        OFFICIAL REPORT           |");
            System.out.println("    ====================================");
            System.out.println("    Date: 2025-03-15");
            System.out.println("    ------------------------------------");
        }

        /** Prints the standard report footer. */
        private static void printFooter() {
            System.out.println("    ------------------------------------");
            System.out.println("    This report is auto-generated.");
            System.out.println("    ====================================");
        }

        // =====================================================================
        // Report methods -- only contain unique content
        // =====================================================================

        /** Generates a student grade report using shared header/footer. */
        static void generateStudentReport(String name, String id,
                                           double gpa) {
            printHeader();  // reuse

            System.out.println("    Student Name: " + name);
            System.out.println("    Student ID:   " + id);
            System.out.println("    GPA:          " + String.format("%.2f", gpa));
            String standing = gpa >= 3.0 ? "Good Standing" : "Probation";
            System.out.println("    Standing:     " + standing);

            printFooter();  // reuse
        }

        /** Generates an employee performance report using shared header/footer. */
        static void generateEmployeeReport(String name, String empId,
                                            double rating) {
            printHeader();  // reuse

            System.out.println("    Employee Name: " + name);
            System.out.println("    Employee ID:   " + empId);
            System.out.println("    Rating:        "
                    + String.format("%.1f", rating) + " / 5.0");
            String level = rating >= 4.0 ? "Exceeds" : "Meets";
            System.out.println("    Performance:   " + level);

            printFooter();  // reuse
        }
    }

    /**
     * Demonstrates the Duplicate Code refactoring.
     */
    public static void demo() {
        System.out.println("  [Duplicate Code - AFTER refactoring]");
        System.out.println("  Common header/footer extracted into shared methods:");
        System.out.println();

        ReportGenerator.generateStudentReport("Eve", "S12345", 3.85);
        System.out.println();
        ReportGenerator.generateEmployeeReport("Frank", "E67890", 4.2);
        System.out.println();
        System.out.println("    Header and footer now live in ONE place.");
        System.out.println("    Change once, affect all reports.");
    }
}
