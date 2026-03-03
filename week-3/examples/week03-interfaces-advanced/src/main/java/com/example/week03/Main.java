package com.example.week03;

import com.example.week03.interfaces.BasicInterfaceDemo;
import com.example.week03.interfaces.NestedInterfaceDemo;
import com.example.week03.interfaces.InterfaceExtensionDemo;
import com.example.week03.interfaces.ModernInterfaceDemo;
import com.example.week03.interfaces.AbstractVsInterfaceDemo;
import com.example.week03.classes.NestedClassDemo;
import com.example.week03.classes.AnonymousClassDemo;
import com.example.week03.enums.EnumDemo;
import com.example.week03.reflection.ReflectionDemo;
import com.example.week03.wrappers.WrapperClassDemo;
import com.example.week03.lambda.LambdaDemo;
import com.example.week03.lambda.FunctionalInterfaceDemo;

/**
 * ============================================================================
 * CEN206 Object-Oriented Programming - Week 03
 * Main Entry Point
 * ============================================================================
 *
 * This program demonstrates the following OOP concepts:
 *
 *   1. Basic Interfaces           - Declaration, implementation, multiple interfaces
 *   2. Nested Interfaces          - Interfaces inside classes and other interfaces
 *   3. Interface Extension        - Extending interfaces, interface inheritance
 *   4. Modern Interface Features  - Default, static, and private methods (Java 9+)
 *   5. Abstract Class vs Interface - Side-by-side comparison
 *   6. Nested Classes             - Inner, static nested, and local classes
 *   7. Anonymous Classes          - Anonymous class creation and usage
 *   8. Enums                      - Basic enums, enums with fields/methods/constructors
 *   9. Reflection                 - Class inspection, method invocation, field access
 *  10. Wrapper Classes            - Autoboxing/unboxing, parsing, utility methods
 *  11. Lambda Expressions         - Lambda syntax, built-in functional interfaces
 *  12. Functional Interfaces      - @FunctionalInterface, custom interfaces, chaining
 *
 * To compile and run:
 *   mvn compile exec:java
 *
 * ============================================================================
 */
public class Main {

    /**
     * A utility method to print a prominent banner for each demo section.
     * This helps students identify where each topic begins in the output.
     */
    public static void printBanner(String title) {
        String border = "=".repeat(72);
        System.out.println();
        System.out.println(border);
        System.out.println("  " + title);
        System.out.println(border);
        System.out.println();
    }

    /**
     * Main entry point. Calls each demo in order.
     * Students can comment out individual demo() calls to focus on one topic.
     */
    public static void main(String[] args) {
        System.out.println("*".repeat(72));
        System.out.println("*  CEN206 Object-Oriented Programming - Week 03 Demonstrations       *");
        System.out.println("*  Topics: Interfaces, Classes, Enums, Reflection, Lambdas           *");
        System.out.println("*".repeat(72));

        // ------------------------------------------------------------------
        // 1. Basic Interface Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 1: Basic Interfaces");
        BasicInterfaceDemo.demo();

        // ------------------------------------------------------------------
        // 2. Nested Interface Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 2: Nested Interfaces");
        NestedInterfaceDemo.demo();

        // ------------------------------------------------------------------
        // 3. Interface Extension Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 3: Interface Extension / Inheritance");
        InterfaceExtensionDemo.demo();

        // ------------------------------------------------------------------
        // 4. Modern Interface Features Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 4: Modern Interface Features (Java 8/9+)");
        ModernInterfaceDemo.demo();

        // ------------------------------------------------------------------
        // 5. Abstract Class vs Interface Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 5: Abstract Class vs Interface");
        AbstractVsInterfaceDemo.demo();

        // ------------------------------------------------------------------
        // 6. Nested Class Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 6: Nested Classes (Inner, Static Nested, Local)");
        NestedClassDemo.demo();

        // ------------------------------------------------------------------
        // 7. Anonymous Class Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 7: Anonymous Classes");
        AnonymousClassDemo.demo();

        // ------------------------------------------------------------------
        // 8. Enum Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 8: Enums");
        EnumDemo.demo();

        // ------------------------------------------------------------------
        // 9. Reflection Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 9: Reflection");
        ReflectionDemo.demo();

        // ------------------------------------------------------------------
        // 10. Wrapper Class Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 10: Wrapper Classes");
        WrapperClassDemo.demo();

        // ------------------------------------------------------------------
        // 11. Lambda Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 11: Lambda Expressions");
        LambdaDemo.demo();

        // ------------------------------------------------------------------
        // 12. Functional Interface Demo
        // ------------------------------------------------------------------
        printBanner("DEMO 12: Functional Interfaces and Chaining");
        FunctionalInterfaceDemo.demo();

        // ------------------------------------------------------------------
        // Done
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("*".repeat(72));
        System.out.println("*  All Week 03 demonstrations completed successfully!                *");
        System.out.println("*".repeat(72));
    }
}
