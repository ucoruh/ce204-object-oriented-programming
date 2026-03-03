package com.example.week01.basics;

/**
 * ==========================================================================
 * DEMO 5: Static Members
 * ==========================================================================
 *
 * The 'static' keyword means a member belongs to the CLASS, not to any
 * particular OBJECT (instance).
 *
 * Static Members:
 *   1. Static Fields    - Shared among ALL instances of the class
 *   2. Static Methods   - Can be called without creating an object
 *   3. Static Blocks    - Run once when the class is first loaded
 *   4. Static Classes   - Nested classes that don't need an outer instance
 *
 * Key Rules:
 *   - Static methods CANNOT access instance (non-static) members directly
 *   - Static methods CANNOT use 'this' keyword
 *   - Instance methods CAN access static members
 *   - Static fields have only ONE copy shared by all objects
 */
public class StaticDemo {

    // ======================================================================
    // Example 1: Counter - static field shared across instances
    // ======================================================================

    /**
     * Demonstrates static fields being shared across all instances.
     * The 'totalStudents' counter tracks how many Student objects exist.
     */
    static class StudentCounter {
        // STATIC field: shared by ALL StudentCounter objects
        // There is only ONE copy of this variable in memory.
        static int totalStudents = 0;

        // INSTANCE fields: each object has its own copy
        String name;
        int id;

        StudentCounter(String name) {
            this.name = name;
            totalStudents++;           // Increment the shared counter
            this.id = totalStudents;   // Assign a unique ID
            System.out.println("  Created student #" + id + ": " + name
                    + " (Total: " + totalStudents + ")");
        }

        // STATIC method: can be called without an object
        static int getTotalStudents() {
            return totalStudents;
        }

        // STATIC method to reset counter
        static void resetCounter() {
            totalStudents = 0;
            System.out.println("  Counter reset to 0");
        }

        // INSTANCE method: can access both static and instance members
        void displayInfo() {
            System.out.println("  Student " + id + ": " + name
                    + " (out of " + totalStudents + " total)");
        }
    }

    // ======================================================================
    // Example 2: MathUtils - utility class with static methods
    // ======================================================================

    /**
     * A utility class with only static methods.
     * This is a common pattern for helper/utility classes.
     * Think of java.lang.Math as a real-world example.
     */
    static class MathUtils {

        // Private constructor prevents instantiation
        // This is a common pattern for utility classes
        private MathUtils() {
            throw new UnsupportedOperationException("Utility class - do not instantiate");
        }

        // Static constant (convention: UPPER_SNAKE_CASE)
        static final double PI = 3.14159265358979;
        static final double E = 2.71828182845905;

        // Static methods - called on the CLASS, not on objects
        static int max(int a, int b) {
            return (a > b) ? a : b;
        }

        static int min(int a, int b) {
            return (a < b) ? a : b;
        }

        static double circleArea(double radius) {
            return PI * radius * radius;
        }

        static boolean isPrime(int n) {
            if (n <= 1) return false;
            if (n <= 3) return true;
            if (n % 2 == 0 || n % 3 == 0) return false;
            for (int i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) return false;
            }
            return true;
        }

        static int factorial(int n) {
            if (n < 0) throw new IllegalArgumentException("Negative input");
            int result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }

    // ======================================================================
    // Example 3: Configuration - static block for initialization
    // ======================================================================

    /**
     * Demonstrates static initialization blocks.
     * Static blocks run ONCE when the class is first loaded into memory.
     * They execute in the order they appear, BEFORE any constructor.
     */
    static class Configuration {
        static String appName;
        static String version;
        static int maxConnections;
        static boolean debugMode;

        // STATIC BLOCK 1: runs when class is loaded
        static {
            System.out.println("  [Static Block 1: Initializing basic config...]");
            appName = "CEN206 Demo App";
            version = "1.0.0";
        }

        // STATIC BLOCK 2: can have multiple static blocks
        static {
            System.out.println("  [Static Block 2: Initializing advanced config...]");
            maxConnections = 100;
            debugMode = false;
        }

        static void displayConfig() {
            System.out.println("  App: " + appName + " v" + version);
            System.out.println("  Max Connections: " + maxConnections);
            System.out.println("  Debug Mode: " + debugMode);
        }
    }

    // ======================================================================
    // Example 4: Static nested class
    // ======================================================================

    /**
     * Demonstrates static nested classes vs. inner classes.
     */
    static class OuterClass {
        static String staticField = "I am a static field of OuterClass";
        String instanceField = "I am an instance field of OuterClass";

        // Static nested class: does NOT need an instance of OuterClass
        static class StaticNested {
            void display() {
                // Can access static members of the outer class
                System.out.println("  StaticNested accessing: " + staticField);
                // CANNOT access instance members:
                // System.out.println(instanceField); // Compile Error!
            }
        }

        // Non-static inner class: NEEDS an instance of OuterClass
        class InnerClass {
            void display() {
                // Can access BOTH static and instance members of outer class
                System.out.println("  InnerClass accessing static: " + staticField);
                System.out.println("  InnerClass accessing instance: " + instanceField);
            }
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates all static member concepts.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Static Fields Shared Across Instances
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Static Fields - Shared Counter]");
        System.out.println();

        // Reset before demo
        StudentCounter.resetCounter();
        System.out.println();

        StudentCounter s1 = new StudentCounter("Alice");
        StudentCounter s2 = new StudentCounter("Bob");
        StudentCounter s3 = new StudentCounter("Charlie");

        System.out.println();
        System.out.println("  All students share the same counter:");
        s1.displayInfo();
        s2.displayInfo();
        s3.displayInfo();

        System.out.println();
        System.out.println("  Static method called on CLASS (no object needed):");
        System.out.println("  StudentCounter.getTotalStudents() = "
                + StudentCounter.getTotalStudents());

        // ------------------------------------------------------------------
        // Part 2: Static Methods (Utility Class)
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Static Methods - Utility Class Pattern]");
        System.out.println();

        // Notice: we call methods on the CLASS NAME, not on an object
        System.out.println("  MathUtils.PI = " + MathUtils.PI);
        System.out.println("  MathUtils.max(10, 20) = " + MathUtils.max(10, 20));
        System.out.println("  MathUtils.min(10, 20) = " + MathUtils.min(10, 20));
        System.out.println("  MathUtils.circleArea(5) = "
                + String.format("%.2f", MathUtils.circleArea(5)));
        System.out.println("  MathUtils.isPrime(17) = " + MathUtils.isPrime(17));
        System.out.println("  MathUtils.isPrime(18) = " + MathUtils.isPrime(18));
        System.out.println("  MathUtils.factorial(6) = " + MathUtils.factorial(6));

        System.out.println();
        System.out.println("  Trying to instantiate utility class...");
        try {
            // MathUtils utils = new MathUtils(); // This would fail
            // Using reflection to demonstrate the protection
            var constructor = MathUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (Exception e) {
            System.out.println("  Exception: " + e.getCause().getMessage());
            System.out.println("  (Utility classes should not be instantiated)");
        }

        // ------------------------------------------------------------------
        // Part 3: Static Blocks
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Static Initialization Blocks]");
        System.out.println();

        System.out.println("  Accessing Configuration class for the first time...");
        System.out.println("  (Static blocks execute now!)");
        System.out.println();
        Configuration.displayConfig();
        System.out.println();
        System.out.println("  Accessing again - static blocks do NOT run again:");
        Configuration.displayConfig();

        // ------------------------------------------------------------------
        // Part 4: Static vs Instance Access Rules
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Static vs Instance Access Rules]");
        System.out.println();

        System.out.println("  Rules:");
        System.out.println("  1. Static methods CAN access static members:      YES");
        System.out.println("  2. Static methods CAN access instance members:    NO");
        System.out.println("  3. Instance methods CAN access static members:    YES");
        System.out.println("  4. Instance methods CAN access instance members:  YES");
        System.out.println("  5. Static methods CAN use 'this':                 NO");

        // ------------------------------------------------------------------
        // Part 5: Static Nested Class
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: Static Nested Class vs Inner Class]");
        System.out.println();

        // Static nested class - no instance of OuterClass needed
        OuterClass.StaticNested nested = new OuterClass.StaticNested();
        nested.display();

        System.out.println();

        // Inner class - requires an instance of OuterClass
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();

        System.out.println();
        System.out.println("  Key difference:");
        System.out.println("  - Static nested class: created independently of outer class");
        System.out.println("  - Inner class: needs an instance of the outer class");
    }
}
