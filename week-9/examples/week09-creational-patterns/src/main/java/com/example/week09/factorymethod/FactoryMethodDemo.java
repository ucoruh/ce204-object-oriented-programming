package com.example.week09.factorymethod;

/**
 * ============================================================================
 * FACTORY METHOD PATTERN - Demo
 * ============================================================================
 *
 * Intent:
 *   Define an interface for creating an object, but let subclasses decide
 *   which class to instantiate. Factory Method lets a class defer
 *   instantiation to subclasses.
 *
 * Structure:
 *   - Product (Button)              : Defines the interface for created objects
 *   - ConcreteProduct (WindowsButton, LinuxButton) : Implements the Product
 *   - Creator (Dialog)              : Declares the factory method
 *   - ConcreteCreator (WindowsDialog, LinuxDialog) : Overrides factory method
 *
 * When to use:
 *   - When a class cannot anticipate the class of objects it must create
 *   - When a class wants its subclasses to specify the objects it creates
 *   - When you want to localize the knowledge of which helper class to use
 *
 * Real-world examples:
 *   - java.util.Calendar.getInstance()
 *   - java.text.NumberFormat.getInstance()
 *   - java.net.URLStreamHandlerFactory
 * ============================================================================
 */
public class FactoryMethodDemo {

    public static void demo() {
        System.out.println("=============================================================");
        System.out.println("  1. FACTORY METHOD PATTERN");
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("  Scenario: A cross-platform UI framework creates dialogs.");
        System.out.println("  Each OS has its own button style, but the dialog logic");
        System.out.println("  (renderWindow) stays the same.");
        System.out.println();

        // Simulate detecting the OS at runtime
        // In practice, this decision could come from configuration or environment
        Dialog dialog;

        // --- Windows platform ---
        System.out.println("  >> Running on Windows:");
        dialog = new WindowsDialog();
        dialog.renderWindow();
        System.out.println();

        // --- Linux platform ---
        System.out.println("  >> Running on Linux:");
        dialog = new LinuxDialog();
        dialog.renderWindow();
        System.out.println();

        // Key takeaway
        System.out.println("  KEY TAKEAWAY:");
        System.out.println("  - The Dialog class does not know which Button it will get.");
        System.out.println("  - Each subclass provides its own createButton() override.");
        System.out.println("  - Adding a new OS (e.g., MacDialog) requires NO changes");
        System.out.println("    to existing code -- just new classes (Open/Closed Principle).");
        System.out.println();
    }
}
