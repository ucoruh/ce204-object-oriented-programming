package com.example.week13.moving;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Extract Class
 * =============================================================================
 *
 * Intent:
 *   Create a new class and move the relevant fields and methods from the
 *   old class into the new class.
 *
 * When to use:
 *   - A class is doing the work of two (or more) classes.
 *   - A subset of fields and methods form a logical group that could
 *     stand on its own.
 *   - Two fields or methods are always used together.
 *
 * Mechanics:
 *   1. Identify a cohesive subset of the class's responsibilities.
 *   2. Create a new class for that subset.
 *   3. Move the relevant fields and methods to the new class.
 *   4. Create a link from the old class to the new class.
 *   5. Adjust visibility as needed.
 *
 * This demo shows a before/after comparison.
 * =============================================================================
 */
public class ExtractClassDemo {

    // =========================================================================
    // BEFORE: Person class has both personal info AND phone number logic
    // =========================================================================

    /**
     * BEFORE: Person manages name, office area code, office number,
     * and phone formatting -- the phone part should be its own class.
     */
    static class PersonBefore {
        private String name;
        private String officeAreaCode;
        private String officeNumber;

        PersonBefore(String name, String areaCode, String number) {
            this.name = name;
            this.officeAreaCode = areaCode;
            this.officeNumber = number;
        }

        String getName() { return name; }

        // These phone methods don't belong on Person
        String getOfficeAreaCode() { return officeAreaCode; }
        String getOfficeNumber() { return officeNumber; }

        String getTelephoneNumber() {
            return "(" + officeAreaCode + ") " + officeNumber;
        }
    }

    // =========================================================================
    // AFTER: TelephoneNumber extracted into its own class
    // =========================================================================

    /**
     * Extracted class: encapsulates phone number data and formatting.
     */
    static class TelephoneNumber {
        private final String areaCode;
        private final String number;

        TelephoneNumber(String areaCode, String number) {
            this.areaCode = areaCode;
            this.number = number;
        }

        String getAreaCode() { return areaCode; }
        String getNumber() { return number; }

        /** Formatting logic lives with the phone data. */
        String getFormatted() {
            return "(" + areaCode + ") " + number;
        }

        @Override
        public String toString() { return getFormatted(); }
    }

    /**
     * AFTER: Person delegates phone concerns to TelephoneNumber.
     */
    static class PersonAfter {
        private String name;
        private TelephoneNumber officePhone;

        PersonAfter(String name, TelephoneNumber officePhone) {
            this.name = name;
            this.officePhone = officePhone;
        }

        String getName() { return name; }
        TelephoneNumber getOfficePhone() { return officePhone; }
    }

    /**
     * Demonstrates Extract Class refactoring.
     */
    public static void demo() {
        System.out.println("  [Extract Class - Before and After]");
        System.out.println();

        // Before
        System.out.println("    BEFORE (phone data embedded in Person):");
        PersonBefore before = new PersonBefore("Dr. Smith", "312", "555-0198");
        System.out.println("      Name:  " + before.getName());
        System.out.println("      Phone: " + before.getTelephoneNumber());

        System.out.println();

        // After
        System.out.println("    AFTER (TelephoneNumber extracted as its own class):");
        TelephoneNumber phone = new TelephoneNumber("312", "555-0198");
        PersonAfter after = new PersonAfter("Dr. Smith", phone);
        System.out.println("      Name:  " + after.getName());
        System.out.println("      Phone: " + after.getOfficePhone());

        System.out.println();
        System.out.println("    TelephoneNumber can now be reused, tested, and");
        System.out.println("    extended independently (e.g., add international format).");
    }
}
