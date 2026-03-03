package com.example.week12.bloaters;

/**
 * =============================================================================
 * CODE SMELL: Large Class / God Class (BEFORE Refactoring)
 * =============================================================================
 *
 * Problem:
 *   A single class that knows too much and does too much.  This "God Class"
 *   handles user data, address formatting, email validation, and notification
 *   sending -- all responsibilities that should live in separate classes.
 *
 * Smell Category: Bloater
 *
 * Why it is bad:
 *   - Violates the Single Responsibility Principle.
 *   - Hard to test any single capability without instantiating everything.
 *   - Any change risks breaking unrelated functionality.
 *   - Multiple developers cannot work on it in parallel without conflicts.
 *
 * Refactoring: Extract Class -- see LargeClassAfter.java
 * =============================================================================
 */
public class LargeClassBefore {

    /**
     * BAD EXAMPLE: One class handles user info, address formatting,
     * email validation, and notification sending.
     */
    static class UserManager {
        // --- User data ---
        private String firstName;
        private String lastName;
        private String email;

        // --- Address data ---
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;

        // --- Notification preferences ---
        private boolean emailNotifications;
        private boolean smsNotifications;
        private String phoneNumber;

        public UserManager(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.emailNotifications = true;  // default
            this.smsNotifications = false;
        }

        // --- User methods ---
        public String getFullName() {
            return firstName + " " + lastName;
        }

        // --- Address methods (should be its own class) ---
        public void setAddress(String street, String city, String state,
                               String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }

        public String getFormattedAddress() {
            return street + "\n" + city + ", " + state + " " + zipCode
                    + "\n" + country;
        }

        public boolean isValidZipCode() {
            return zipCode != null && zipCode.matches("\\d{5}(-\\d{4})?");
        }

        // --- Email validation (should be its own class or utility) ---
        public boolean isValidEmail() {
            return email != null && email.contains("@") && email.contains(".");
        }

        // --- Notification methods (should be its own class) ---
        public void setNotificationPreferences(boolean emailPref,
                                                boolean smsPref,
                                                String phone) {
            this.emailNotifications = emailPref;
            this.smsNotifications = smsPref;
            this.phoneNumber = phone;
        }

        public void sendNotification(String message) {
            if (emailNotifications) {
                System.out.println("    Sending email to " + email
                        + ": " + message);
            }
            if (smsNotifications && phoneNumber != null) {
                System.out.println("    Sending SMS to " + phoneNumber
                        + ": " + message);
            }
        }

        public void printUserSummary() {
            System.out.println("    Name:    " + getFullName());
            System.out.println("    Email:   " + email
                    + (isValidEmail() ? " (valid)" : " (INVALID)"));
            if (street != null) {
                System.out.println("    Address: " + street + ", " + city
                        + ", " + state + " " + zipCode);
                System.out.println("    Zip OK:  " + isValidZipCode());
            }
            System.out.println("    Notify:  email=" + emailNotifications
                    + ", sms=" + smsNotifications);
        }
    }

    /**
     * Demonstrates the Large Class code smell.
     */
    public static void demo() {
        System.out.println("  [Large Class - BEFORE refactoring]");
        System.out.println("  One UserManager class handles everything:");
        System.out.println();

        UserManager user = new UserManager("Bob", "Smith", "bob@example.com");
        user.setAddress("123 Main St", "Springfield", "IL", "62704", "USA");
        user.setNotificationPreferences(true, true, "+1-555-0123");
        user.printUserSummary();
        user.sendNotification("Your order has shipped!");
    }
}
