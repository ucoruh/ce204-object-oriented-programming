package com.example.week02.polymorphism;

/**
 * ==========================================================================
 * DEMO 7: Abstract Classes
 * ==========================================================================
 *
 * An ABSTRACT CLASS is a class that:
 *   - Cannot be instantiated directly (no: new AbstractClass())
 *   - Can contain both abstract and concrete (regular) methods
 *   - Can contain fields, constructors, static methods
 *   - Is meant to be EXTENDED by concrete subclasses
 *
 * An ABSTRACT METHOD:
 *   - Has no body (just a declaration with a semicolon)
 *   - MUST be implemented by concrete subclasses
 *   - Can only exist in an abstract class
 *
 * Abstract vs Interface (simplified):
 *   - Abstract class: partial implementation, single inheritance
 *   - Interface: pure contract (Java 8+ allows default methods)
 *
 * Use Abstract Classes When:
 *   - You want to share code among related classes
 *   - You expect subclasses to have common methods/fields
 *   - You want to declare non-static, non-final fields
 *   - You want to define a template that subclasses must follow
 */
public class AbstractClassDemo {

    // ======================================================================
    // Example 1: Shape hierarchy with abstract methods
    // ======================================================================

    /**
     * Abstract Shape class - defines the contract for all shapes.
     * Every shape MUST provide area() and perimeter() implementations.
     */
    static abstract class Shape {
        // Abstract classes CAN have fields
        String name;
        String color;

        // Abstract classes CAN have constructors (called via super())
        Shape(String name, String color) {
            this.name = name;
            this.color = color;
        }

        // ABSTRACT methods: no body, subclasses MUST implement these
        abstract double area();
        abstract double perimeter();

        // CONCRETE methods: have a body, inherited by subclasses
        void display() {
            System.out.println("    " + color + " " + name + ":");
            System.out.println("      Area: " + String.format("%.2f", area()));
            System.out.println("      Perimeter: " + String.format("%.2f", perimeter()));
        }

        // Concrete method that uses abstract methods
        boolean isLargerThan(Shape other) {
            return this.area() > other.area();
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(double radius, String color) {
            super("Circle", color);
            this.radius = radius;
        }

        // MUST implement abstract methods
        @Override
        double area() {
            return Math.PI * radius * radius;
        }

        @Override
        double perimeter() {
            return 2 * Math.PI * radius;
        }
    }

    static class Rectangle extends Shape {
        double width, height;

        Rectangle(double width, double height, String color) {
            super("Rectangle", color);
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }

        @Override
        double perimeter() {
            return 2 * (width + height);
        }
    }

    static class EquilateralTriangle extends Shape {
        double side;

        EquilateralTriangle(double side, String color) {
            super("Equilateral Triangle", color);
            this.side = side;
        }

        @Override
        double area() {
            return (Math.sqrt(3) / 4) * side * side;
        }

        @Override
        double perimeter() {
            return 3 * side;
        }
    }

    // ======================================================================
    // Example 2: Template Method Pattern using abstract classes
    // ======================================================================

    /**
     * The Template Method Pattern defines the skeleton of an algorithm
     * in the base class, letting subclasses fill in the specific steps.
     *
     * This is one of the most powerful uses of abstract classes!
     */
    static abstract class DataProcessor {

        // The TEMPLATE METHOD: defines the algorithm structure
        // This is a CONCRETE method that calls abstract methods
        final void process() {
            System.out.println("    --- Processing started ---");
            String data = readData();
            String processed = processData(data);
            writeData(processed);
            System.out.println("    --- Processing complete ---");
        }

        // Abstract steps: subclasses provide the specific implementation
        abstract String readData();
        abstract String processData(String data);
        abstract void writeData(String data);
    }

    static class CSVProcessor extends DataProcessor {
        @Override
        String readData() {
            System.out.println("    Reading CSV file...");
            return "name,age,grade\nAlice,20,A\nBob,21,B";
        }

        @Override
        String processData(String data) {
            System.out.println("    Parsing CSV data...");
            return data.replace(",", " | ");
        }

        @Override
        void writeData(String data) {
            System.out.println("    Writing formatted table:");
            System.out.println("    " + data.replace("\n", "\n    "));
        }
    }

    static class JSONProcessor extends DataProcessor {
        @Override
        String readData() {
            System.out.println("    Reading JSON data...");
            return "{\"students\": [{\"name\": \"Alice\"}, {\"name\": \"Bob\"}]}";
        }

        @Override
        String processData(String data) {
            System.out.println("    Transforming JSON data...");
            return data.toUpperCase();
        }

        @Override
        void writeData(String data) {
            System.out.println("    Writing JSON output:");
            System.out.println("    " + data);
        }
    }

    // ======================================================================
    // Example 3: Abstract class with partial implementation
    // ======================================================================

    /**
     * An abstract class can implement SOME methods and leave others abstract.
     * This allows sharing common code while requiring specific behavior.
     */
    static abstract class AbstractLogger {
        private int logCount = 0;

        // Concrete method: common to all loggers
        void log(String level, String message) {
            logCount++;
            String formatted = formatMessage(level, message);
            writeLog(formatted);
        }

        // Concrete method: available to all subclasses
        int getLogCount() {
            return logCount;
        }

        // Concrete method: default implementation
        String formatMessage(String level, String message) {
            return "[" + level + "] " + message;
        }

        // Abstract method: each logger writes differently
        abstract void writeLog(String formattedMessage);
    }

    static class ConsoleLogger extends AbstractLogger {
        @Override
        void writeLog(String formattedMessage) {
            System.out.println("    CONSOLE: " + formattedMessage);
        }
    }

    static class FileLogger extends AbstractLogger {
        private final java.util.List<String> fileContent = new java.util.ArrayList<>();

        @Override
        void writeLog(String formattedMessage) {
            fileContent.add(formattedMessage);
            System.out.println("    FILE:    " + formattedMessage + " (saved to file)");
        }

        // Additional method specific to FileLogger
        void printFileContents() {
            System.out.println("    File contains " + fileContent.size() + " entries");
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Abstract Class Cannot Be Instantiated
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Abstract Classes Cannot Be Instantiated]");
        System.out.println();

        // This would cause a compile error:
        // Shape shape = new Shape("Test", "Red");  // COMPILE ERROR!

        System.out.println("  Shape shape = new Shape(\"Test\", \"Red\");  // COMPILE ERROR!");
        System.out.println("  Abstract classes MUST be extended by a concrete class.");
        System.out.println("  But we CAN use abstract class as a reference type:");
        System.out.println();

        // We CAN use abstract class as a reference type (polymorphism)
        Shape circle = new Circle(5.0, "Red");
        Shape rect = new Rectangle(4.0, 6.0, "Blue");
        Shape tri = new EquilateralTriangle(5.0, "Green");

        // ------------------------------------------------------------------
        // Part 2: Using Abstract Methods
        // ------------------------------------------------------------------
        System.out.println("[Part 2: Shapes Using Abstract Methods]");
        System.out.println();

        circle.display();
        System.out.println();
        rect.display();
        System.out.println();
        tri.display();

        System.out.println();
        System.out.println("  Using concrete method isLargerThan():");
        System.out.println("  Circle larger than Rectangle? "
                + circle.isLargerThan(rect));

        // ------------------------------------------------------------------
        // Part 3: Polymorphic Array of Abstract Type
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Polymorphic Array of Abstract Type]");
        System.out.println();

        Shape[] shapes = { circle, rect, tri };
        double totalArea = 0;
        for (Shape s : shapes) {
            totalArea += s.area();  // Calls the CORRECT overridden version
        }
        System.out.println("  Total area of all shapes: " + String.format("%.2f", totalArea));

        // ------------------------------------------------------------------
        // Part 4: Template Method Pattern
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Template Method Pattern]");
        System.out.println();

        System.out.println("  CSV Processing:");
        DataProcessor csvProc = new CSVProcessor();
        csvProc.process();

        System.out.println();
        System.out.println("  JSON Processing:");
        DataProcessor jsonProc = new JSONProcessor();
        jsonProc.process();

        System.out.println();
        System.out.println("  The process() method is the TEMPLATE METHOD:");
        System.out.println("  It defines the algorithm (read -> process -> write)");
        System.out.println("  Subclasses provide the specific read/process/write steps");

        // ------------------------------------------------------------------
        // Part 5: Partial Implementation
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: Abstract Class with Partial Implementation]");
        System.out.println();

        AbstractLogger console = new ConsoleLogger();
        FileLogger file = new FileLogger();

        console.log("INFO", "Application started");
        console.log("WARN", "Low memory");
        file.log("INFO", "User logged in");
        file.log("ERROR", "Database connection failed");

        System.out.println();
        System.out.println("  Console logger count: " + console.getLogCount());
        System.out.println("  File logger count: " + file.getLogCount());
        file.printFileContents();

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Abstract Classes]");
        System.out.println("  - Cannot be instantiated (no 'new AbstractClass()')");
        System.out.println("  - Can have abstract AND concrete methods");
        System.out.println("  - Can have fields and constructors");
        System.out.println("  - Subclasses MUST implement all abstract methods");
        System.out.println("  - Perfect for the Template Method pattern");
        System.out.println("  - Use when classes share common code and structure");
    }
}
