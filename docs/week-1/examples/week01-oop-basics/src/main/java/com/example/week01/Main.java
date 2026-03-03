package com.example.week01;

import com.example.week01.basics.ClassAndObjectDemo;
import com.example.week01.basics.MethodOverloadingDemo;
import com.example.week01.basics.ConstructorDemo;
import com.example.week01.basics.AccessModifierDemo;
import com.example.week01.basics.StaticDemo;
import com.example.week01.basics.EncapsulationDemo;
import com.example.week01.basics.OCSFDemo;

/**
 * ==========================================================================
 * CEN206 Object-Oriented Programming - Week 01: OOP Basics
 * ==========================================================================
 *
 * This is the main entry point for all Week 01 demonstrations.
 * Each demo covers a fundamental concept of Object-Oriented Programming
 * in Java.
 *
 * Topics Covered:
 *   1. Classes and Objects      - The building blocks of OOP
 *   2. Method Overloading       - Same name, different parameters
 *   3. Constructors             - Object initialization
 *   4. Access Modifiers         - Controlling visibility
 *   5. Static Members           - Class-level data and behavior
 *   6. Encapsulation            - Data hiding and protection
 *   7. OCSF Pattern             - Reusable framework design
 *
 * How to run:
 *   mvn compile exec:java
 *
 * @author CEN206 Course Team
 * @version 1.0
 */
public class Main {

    /**
     * Prints a formatted section header to the console.
     * This helper method makes the output easier to read by
     * clearly separating each demo section.
     *
     * @param title the title of the demo section
     */
    public static void printHeader(String title) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  " + title);
        System.out.println("=".repeat(70));
        System.out.println();
    }

    /**
     * Prints a sub-section header within a demo.
     *
     * @param subtitle the subtitle text
     */
    public static void printSubHeader(String subtitle) {
        System.out.println();
        System.out.println("-".repeat(50));
        System.out.println("  " + subtitle);
        System.out.println("-".repeat(50));
    }

    /**
     * Main method - runs all demonstrations in sequence.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("*".repeat(70));
        System.out.println("*" + " ".repeat(68) + "*");
        System.out.println("*   CEN206 Object-Oriented Programming                              *");
        System.out.println("*   Week 01: OOP Basics - Java Fundamentals                         *");
        System.out.println("*" + " ".repeat(68) + "*");
        System.out.println("*".repeat(70));

        // Demo 1: Classes and Objects
        printHeader("DEMO 1: Classes and Objects");
        ClassAndObjectDemo.demo();

        // Demo 2: Method Overloading
        printHeader("DEMO 2: Method Overloading");
        MethodOverloadingDemo.demo();

        // Demo 3: Constructors
        printHeader("DEMO 3: Constructors");
        ConstructorDemo.demo();

        // Demo 4: Access Modifiers
        printHeader("DEMO 4: Access Modifiers");
        AccessModifierDemo.demo();

        // Demo 5: Static Members
        printHeader("DEMO 5: Static Members");
        StaticDemo.demo();

        // Demo 6: Encapsulation
        printHeader("DEMO 6: Encapsulation");
        EncapsulationDemo.demo();

        // Demo 7: OCSF Reusable Framework Pattern
        printHeader("DEMO 7: OCSF - Reusable Framework Pattern");
        OCSFDemo.demo();

        // Closing
        System.out.println();
        System.out.println("*".repeat(70));
        System.out.println("*   All Week 01 demos completed successfully!                       *");
        System.out.println("*".repeat(70));
        System.out.println();
    }
}
