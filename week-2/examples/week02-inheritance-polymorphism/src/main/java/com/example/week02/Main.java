package com.example.week02;

import com.example.week02.inheritance.InheritanceDemo;
import com.example.week02.inheritance.ConstructorChainDemo;
import com.example.week02.inheritance.SuperThisDemo;
import com.example.week02.inheritance.InstanceofDemo;
import com.example.week02.polymorphism.MethodOverridingDemo;
import com.example.week02.polymorphism.PolymorphismDemo;
import com.example.week02.polymorphism.AbstractClassDemo;
import com.example.week02.advanced.FinalKeywordDemo;
import com.example.week02.advanced.ObjectClassDemo;
import com.example.week02.advanced.EncapsulationPatternsDemo;

/**
 * ==========================================================================
 * CEN206 Object-Oriented Programming - Week 02: Inheritance & Polymorphism
 * ==========================================================================
 *
 * This is the main entry point for all Week 02 demonstrations.
 *
 * Topics Covered:
 *   1. Basic Inheritance         - IS-A relationship, extends keyword
 *   2. Constructor Chaining      - super() calls, ordering
 *   3. super and this Keywords   - Accessing parent/current members
 *   4. instanceof Operator       - Runtime type checking
 *   5. Method Overriding         - Redefining inherited behavior
 *   6. Polymorphism              - Runtime method dispatch
 *   7. Abstract Classes          - Partial implementations
 *   8. final Keyword             - Preventing modification
 *   9. Object Class              - The root of all classes
 *  10. Encapsulation Patterns    - Builder pattern, immutability
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
     */
    public static void printHeader(String title) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  " + title);
        System.out.println("=".repeat(70));
        System.out.println();
    }

    /**
     * Prints a sub-section header.
     */
    public static void printSubHeader(String subtitle) {
        System.out.println();
        System.out.println("-".repeat(50));
        System.out.println("  " + subtitle);
        System.out.println("-".repeat(50));
    }

    /**
     * Main method - runs all demonstrations in sequence.
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("*".repeat(70));
        System.out.println("*" + " ".repeat(68) + "*");
        System.out.println("*   CEN206 Object-Oriented Programming                              *");
        System.out.println("*   Week 02: Inheritance & Polymorphism                              *");
        System.out.println("*" + " ".repeat(68) + "*");
        System.out.println("*".repeat(70));

        // --- Inheritance Section ---
        printHeader("DEMO 1: Basic Inheritance");
        InheritanceDemo.demo();

        printHeader("DEMO 2: Constructor Chaining in Inheritance");
        ConstructorChainDemo.demo();

        printHeader("DEMO 3: super and this Keywords");
        SuperThisDemo.demo();

        printHeader("DEMO 4: instanceof Operator");
        InstanceofDemo.demo();

        // --- Polymorphism Section ---
        printHeader("DEMO 5: Method Overriding");
        MethodOverridingDemo.demo();

        printHeader("DEMO 6: Polymorphism");
        PolymorphismDemo.demo();

        printHeader("DEMO 7: Abstract Classes");
        AbstractClassDemo.demo();

        // --- Advanced Section ---
        printHeader("DEMO 8: final Keyword");
        FinalKeywordDemo.demo();

        printHeader("DEMO 9: Object Class Methods");
        ObjectClassDemo.demo();

        printHeader("DEMO 10: Encapsulation Patterns");
        EncapsulationPatternsDemo.demo();

        // Closing
        System.out.println();
        System.out.println("*".repeat(70));
        System.out.println("*   All Week 02 demos completed successfully!                       *");
        System.out.println("*".repeat(70));
        System.out.println();
    }
}
