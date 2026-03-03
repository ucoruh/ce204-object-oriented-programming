package com.example.week12.bloaters;

/**
 * =============================================================================
 * CODE SMELL FIX: Large Class / God Class (AFTER Refactoring)
 * =============================================================================
 *
 * Solution Applied: Extract Class
 *
 *   The monolithic UserManager has been split into focused classes:
 *     - Address              -- responsible for address data and formatting
 *     - EmailValidator       -- responsible for email validation
 *     - NotificationService  -- responsible for sending notifications
 *     - User                 -- responsible only for user identity
 *
 * Benefits:
 *   - Each class has a single, clear responsibility.
 *   - Classes can be tested, reused, and modified independently.
 *   - Teams can work on Address, Notification, etc. without conflicts.
 *   - Easier to extend (e.g., add push notifications) without touching User.
 * =============================================================================
 */
public class LargeClassAfter {

    // =========================================================================
    // Extracted Class 1: Address
    // =========================================================================

    /**
     * Encapsulates address data and related operations.
     */
    static class Address {
        private final String street;
        private final String city;
        private final String state;
        private final String zipCode;
        private final String country;

        public Address(String street, String city, String state,
                       String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }

        /** Returns a formatted multi-line address string. */
        public String getFormatted() {
            return street + ", " + city + ", " + state + " " + zipCode;
        }

        /** Validates a US zip code (5 digits, optionally plus 4). */
        public boolean isValidZipCode() {
            return zipCode != null && zipCode.matches("\\d{5}(-\\d{4})?");
        }
    }

    // =========================================================================
    // Extracted Class 2: EmailValidator
    // =========================================================================

    /**
     * Simple email validation utility.
     */
    static class EmailValidator {
        /** Checks that the email has a basic valid structure. */
        public static boolean isValid(String email) {
            return email != null && email.contains("@") && email.contains(".");
        }
    }

    // =========================================================================
    // Extracted Class 3: NotificationService
    // =========================================================================

    /**
     * Handles sending notifications through various channels.
     */
    static class NotificationService {
        private boolean emailEnabled;
        private boolean smsEnabled;
        private String phoneNumber;

        public NotificationService(boolean emailEnabled, boolean smsEnabled,
                                    String phoneNumber) {
            this.emailEnabled = emailEnabled;
            this.smsEnabled = smsEnabled;
            this.phoneNumber = phoneNumber;
        }

        /** Sends a notification via all enabled channels. */
        public void send(String recipientEmail, String message) {
            if (emailEnabled) {
                System.out.println("    Sending email to " + recipientEmail
                        + ": " + message);
            }
            if (smsEnabled && phoneNumber != null) {
                System.out.println("    Sending SMS to " + phoneNumber
                        + ": " + message);
            }
        }
    }

    // =========================================================================
    // Refactored User class -- now thin and focused
    // =========================================================================

    /**
     * GOOD EXAMPLE: User now only manages identity. Address, validation,
     * and notification concerns live in their own classes.
     */
    static class User {
        private final String firstName;
        private final String lastName;
        private final String email;
        private Address address;
        private NotificationService notificationService;

        public User(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public void setNotificationService(NotificationService service) {
            this.notificationService = service;
        }

        public void printSummary() {
            System.out.println("    Name:    " + getFullName());
            System.out.println("    Email:   " + email
                    + (EmailValidator.isValid(email) ? " (valid)" : " (INVALID)"));
            if (address != null) {
                System.out.println("    Address: " + address.getFormatted());
                System.out.println("    Zip OK:  " + address.isValidZipCode());
            }
        }

        public void notify(String message) {
            if (notificationService != null) {
                notificationService.send(email, message);
            }
        }
    }

    /**
     * Demonstrates the Large Class refactoring.
     */
    public static void demo() {
        System.out.println("  [Large Class - AFTER refactoring]");
        System.out.println("  Responsibilities split into focused classes:");
        System.out.println();

        // Each concern is now a separate, testable object
        User user = new User("Bob", "Smith", "bob@example.com");
        user.setAddress(new Address("123 Main St", "Springfield",
                "IL", "62704", "USA"));
        user.setNotificationService(
                new NotificationService(true, true, "+1-555-0123"));

        user.printSummary();
        user.notify("Your order has shipped!");
    }
}
