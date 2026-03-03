package com.example.week14;

import com.example.week14.ecommerce.ECommerceDemo;
import com.example.week14.texteditor.TextEditorDemo;
import com.example.week14.notification.NotificationDemo;

/**
 * =============================================================================
 * CEN206 - Object-Oriented Programming
 * Week 14 - Case Studies: Applying Design Patterns to Real-World Projects
 * =============================================================================
 *
 * This project presents three complete case studies that demonstrate how
 * multiple design patterns and refactoring principles work together in
 * realistic software systems.
 *
 * Case Studies:
 *
 *   1. E-Commerce System
 *      Patterns: Strategy (payment), Observer (order events)
 *      Shows how to handle multiple payment methods and decouple
 *      order processing from side effects (email, inventory).
 *
 *   2. Text Editor
 *      Patterns: Command (operations), Memento (undo/redo)
 *      Shows how to make operations reversible and maintain
 *      a history of editor states for undo.
 *
 *   3. Notification Service
 *      Patterns: Decorator (notification enrichment), Factory (creation)
 *      Shows how to dynamically compose notification channels
 *      and simplify creation with a factory.
 *
 * How to run:
 *   mvn compile exec:java
 *
 * @author CEN206 Course
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println(" CEN206 - Week 14: Case Studies");
        System.out.println(" Applying Design Patterns to Real-World Projects");
        System.out.println("=============================================================");
        System.out.println();

        // -----------------------------------------------------------------
        // Case Study 1: E-Commerce System
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  CASE STUDY 1: E-COMMERCE SYSTEM");
        System.out.println("  Patterns: Strategy (payment) + Observer (order events)");
        System.out.println("*************************************************************");
        System.out.println();
        ECommerceDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Case Study 2: Text Editor
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  CASE STUDY 2: TEXT EDITOR");
        System.out.println("  Patterns: Command (operations) + Memento (undo/redo)");
        System.out.println("*************************************************************");
        System.out.println();
        TextEditorDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Case Study 3: Notification Service
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  CASE STUDY 3: NOTIFICATION SERVICE");
        System.out.println("  Patterns: Decorator (enrichment) + Factory (creation)");
        System.out.println("*************************************************************");
        System.out.println();
        NotificationDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Summary
        // -----------------------------------------------------------------
        System.out.println("=============================================================");
        System.out.println(" SUMMARY");
        System.out.println("=============================================================");
        System.out.println(" Real-world systems rarely use a single pattern in isolation.");
        System.out.println(" Effective OO design combines multiple patterns:");
        System.out.println();
        System.out.println("   - Strategy + Observer: flexible algorithms + loose coupling");
        System.out.println("   - Command + Memento: reversible operations + state history");
        System.out.println("   - Decorator + Factory: dynamic composition + clean creation");
        System.out.println();
        System.out.println(" The key is recognizing WHEN to apply each pattern and");
        System.out.println(" HOW they complement each other.");
        System.out.println("=============================================================");
    }
}
