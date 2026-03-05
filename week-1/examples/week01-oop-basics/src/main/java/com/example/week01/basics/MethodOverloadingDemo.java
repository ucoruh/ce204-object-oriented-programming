package com.example.week01.basics;

/**
 * ==========================================================================
 * DEMO 2: Method Overloading
 * ==========================================================================
 *
 * METHOD OVERLOADING means defining multiple methods with the SAME NAME
 * but DIFFERENT PARAMETER LISTS in the same class.
 *
 * The compiler determines which method to call based on:
 *   1. The NUMBER of arguments
 *   2. The TYPES of arguments
 *   3. The ORDER of argument types
 *
 * Important Rules:
 *   - Return type alone does NOT distinguish overloaded methods
 *   - Parameter names alone do NOT distinguish overloaded methods
 *   - Overloading is resolved at COMPILE TIME (static binding)
 *
 * Why use overloading?
 *   - Provides a consistent interface for similar operations
 *   - Makes code more readable and easier to use
 *   - Reduces the need for different method names for similar tasks
 */
public class MethodOverloadingDemo {

    // ======================================================================
    // Example 1: Calculator - overloading by number and type of parameters
    // ======================================================================

    /**
     * A Calculator class demonstrating method overloading.
     * The add() method is overloaded to work with different types and counts.
     */
    static class Calculator {

        // add() with two integers
        int add(int a, int b) {
            System.out.println("  Calling add(int, int)");
            return a + b;
        }

        // add() with three integers - different NUMBER of parameters
        int add(int a, int b, int c) {
            System.out.println("  Calling add(int, int, int)");
            return a + b + c;
        }

        // add() with two doubles - different TYPE of parameters
        double add(double a, double b) {
            System.out.println("  Calling add(double, double)");
            return a + b;
        }

        // add() with String concatenation - completely different types
        String add(String a, String b) {
            System.out.println("  Calling add(String, String)");
            return a + b;
        }

        // add() with mixed types - int and double
        double add(int a, double b) {
            System.out.println("  Calling add(int, double)");
            return a + b;
        }

        // add() with mixed types in DIFFERENT ORDER
        double add(double a, int b) {
            System.out.println("  Calling add(double, int)");
            return a + b;
        }
    }

    // ======================================================================
    // Example 2: Printer - overloading with various data types
    // ======================================================================

    /**
     * Demonstrates overloading a print method to handle different data types.
     * This is similar to how System.out.println() works in Java!
     */
    static class Printer {

        void print(int value) {
            System.out.println("  [int]    : " + value);
        }

        void print(double value) {
            System.out.println("  [double] : " + value);
        }

        void print(String value) {
            System.out.println("  [String] : " + value);
        }

        void print(boolean value) {
            System.out.println("  [boolean]: " + value);
        }

        void print(char value) {
            System.out.println("  [char]   : " + value);
        }

        // Overloaded with array parameter
        void print(int[] values) {
            System.out.print("  [int[]]  : [");
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i]);
                if (i < values.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    // ======================================================================
    // Example 3: ShapeArea - practical overloading for area calculation
    // ======================================================================

    /**
     * Calculates areas for different shapes using overloaded methods.
     * The method name 'calculateArea' is the same, but the parameters
     * tell the compiler which shape we mean.
     */
    static class ShapeArea {

        // Area of a circle (one parameter: radius)
        double calculateArea(double radius) {
            System.out.println("  Calculating area of circle (radius=" + radius + ")");
            return Math.PI * radius * radius;
        }

        // Area of a rectangle (two parameters: width and height)
        double calculateArea(double width, double height) {
            System.out.println("  Calculating area of rectangle (" + width + " x " + height + ")");
            return width * height;
        }

        // Area of a triangle (three parameters: base, height, and a flag)
        double calculateArea(double base, double height, boolean isTriangle) {
            if (isTriangle) {
                System.out.println("  Calculating area of triangle (base=" + base + ", height=" + height + ")");
                return 0.5 * base * height;
            }
            return calculateArea(base, height); // Delegates to rectangle version
        }

        // Area of a square (one int parameter to distinguish from circle)
        double calculateArea(int side) {
            System.out.println("  Calculating area of square (side=" + side + ")");
            return (double) side * side;
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates all method overloading examples.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Calculator Overloading
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Calculator - Overloaded add() methods]");
        System.out.println();

        Calculator calc = new Calculator();

        System.out.println("  add(5, 3)          = " + calc.add(5, 3));
        System.out.println("  add(5, 3, 2)       = " + calc.add(5, 3, 2));
        System.out.println("  add(2.5, 3.7)      = " + calc.add(2.5, 3.7));
        System.out.println("  add(\"Hello\", \" World\") = " + calc.add("Hello", " World"));
        System.out.println("  add(5, 3.14)       = " + calc.add(5, 3.14));
        System.out.println("  add(3.14, 5)       = " + calc.add(3.14, 5));

        // ------------------------------------------------------------------
        // Part 2: Type-based Overloading
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Printer - Type-based Overloading]");
        System.out.println();

        Printer printer = new Printer();

        printer.print(42);                          // calls print(int)
        printer.print(3.14);                        // calls print(double)
        printer.print("Hello, CEN206!");            // calls print(String)
        printer.print(true);                        // calls print(boolean)
        printer.print('A');                         // calls print(char)
        printer.print(new int[]{1, 2, 3, 4, 5});   // calls print(int[])

        // ------------------------------------------------------------------
        // Part 3: Practical Example - Shape Areas
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: ShapeArea - Practical Overloading]");
        System.out.println();

        ShapeArea shape = new ShapeArea();

        double circleArea = shape.calculateArea(5.0);
        System.out.println("  Circle area: " + String.format("%.2f", circleArea));
        System.out.println();

        double rectArea = shape.calculateArea(4.0, 6.0);
        System.out.println("  Rectangle area: " + String.format("%.2f", rectArea));
        System.out.println();

        double triangleArea = shape.calculateArea(4.0, 6.0, true);
        System.out.println("  Triangle area: " + String.format("%.2f", triangleArea));
        System.out.println();

        double squareArea = shape.calculateArea(5);
        System.out.println("  Square area: " + String.format("%.2f", squareArea));

        // ------------------------------------------------------------------
        // Part 4: Automatic Type Promotion
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Automatic Type Promotion in Overloading]");
        System.out.println();

        System.out.println("  When an exact match is not found, Java promotes");
        System.out.println("  the argument type: byte -> short -> int -> long -> float -> double");
        System.out.println();

        // If we pass a byte to calc.add(), it gets promoted to int
        byte b1 = 10, b2 = 20;
        System.out.println("  add(byte, byte) -> promotes to add(int, int)");
        System.out.println("  Result: " + calc.add(b1, b2));

        // float gets promoted to double
        float f1 = 1.5f, f2 = 2.5f;
        System.out.println("  add(float, float) -> promotes to add(double, double)");
        System.out.println("  Result: " + calc.add(f1, f2));

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary]");
        System.out.println("  - Overloading = same method name, different parameter list");
        System.out.println("  - Resolved at compile time (static/early binding)");
        System.out.println("  - Return type alone cannot distinguish overloaded methods");
        System.out.println("  - Java automatically promotes types if no exact match exists");
    }
}
