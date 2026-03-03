package com.example.week12.ooabusers;

/**
 * =============================================================================
 * CODE SMELL FIX: Switch Statements on Type (AFTER Refactoring)
 * =============================================================================
 *
 * Solution Applied: Replace Conditional with Polymorphism
 *
 *   Instead of a type code and switch blocks, we use an abstract base class
 *   (or interface) with concrete subclasses.  Each subclass provides its own
 *   implementation of area(), perimeter(), and describe().
 *
 * Benefits:
 *   - Adding a new shape requires creating ONE new subclass -- nothing else.
 *   - Open/Closed Principle: open for extension, closed for modification.
 *   - No risk of forgetting a case in a switch statement.
 *   - The compiler enforces that every subclass implements all operations.
 * =============================================================================
 */
public class SwitchStatementAfter {

    // =========================================================================
    // Abstract base class
    // =========================================================================

    /**
     * GOOD EXAMPLE: Each shape knows how to compute its own area,
     * perimeter, and description.
     */
    static abstract class Shape {
        abstract double area();
        abstract double perimeter();
        abstract String describe();
    }

    // =========================================================================
    // Concrete shape classes
    // =========================================================================

    static class Circle extends Shape {
        private final double radius;

        Circle(double radius) { this.radius = radius; }

        @Override double area() { return Math.PI * radius * radius; }
        @Override double perimeter() { return 2 * Math.PI * radius; }
        @Override String describe() { return "Circle with radius " + radius; }
    }

    static class Rectangle extends Shape {
        private final double width;
        private final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override double area() { return width * height; }
        @Override double perimeter() { return 2 * (width + height); }
        @Override String describe() {
            return "Rectangle " + width + "x" + height;
        }
    }

    static class Triangle extends Shape {
        private final double base;
        private final double height;

        Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override double area() { return 0.5 * base * height; }
        @Override double perimeter() {
            double hyp = Math.sqrt(base * base + height * height);
            return base + height + hyp;
        }
        @Override String describe() {
            return "Triangle with base " + base + " and height " + height;
        }
    }

    /**
     * Demonstrates the polymorphic refactoring.
     */
    public static void demo() {
        System.out.println("  [Switch Statement - AFTER refactoring]");
        System.out.println("  Polymorphism replaces switch blocks:");
        System.out.println();

        // All shapes share a common interface -- no type checking needed
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 4)
        };

        for (Shape s : shapes) {
            System.out.println("    " + s.describe());
            System.out.println("      Area:      "
                    + String.format("%.2f", s.area()));
            System.out.println("      Perimeter: "
                    + String.format("%.2f", s.perimeter()));
        }

        System.out.println();
        System.out.println("    Adding a Pentagon? Just create a Pentagon class");
        System.out.println("    that extends Shape -- no existing code changes!");
    }
}
