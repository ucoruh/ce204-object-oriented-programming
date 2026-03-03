package com.example.week12.ooabusers;

/**
 * =============================================================================
 * CODE SMELL: Switch Statements on Type (BEFORE Refactoring)
 * =============================================================================
 *
 * Problem:
 *   Using switch/if-else chains to choose behavior based on a type code.
 *   Every time a new type is added, every switch statement must be found
 *   and updated -- a maintenance nightmare and a violation of the
 *   Open/Closed Principle.
 *
 * Smell Category: OO Abuser
 *
 * Why it is bad:
 *   - Adding a new shape requires modifying EVERY switch/if-else block.
 *   - Easy to forget a case in one of the many switch statements.
 *   - The type knowledge is duplicated across the codebase.
 *   - Does not leverage the OO type system.
 *
 * Refactoring: Replace Conditional with Polymorphism
 *              -- see SwitchStatementAfter.java
 * =============================================================================
 */
public class SwitchStatementBefore {

    /**
     * BAD EXAMPLE: Shape represented as a type code with switch statements
     * for every operation.
     */
    static class Shape {
        String type;  // "CIRCLE", "RECTANGLE", "TRIANGLE"
        double param1;
        double param2;

        Shape(String type, double param1, double param2) {
            this.type = type;
            this.param1 = param1;
            this.param2 = param2;
        }
    }

    /**
     * BAD: Every operation has its own switch block.
     * Adding a new shape means updating ALL of these methods.
     */
    static double calculateArea(Shape shape) {
        switch (shape.type) {
            case "CIRCLE":
                return Math.PI * shape.param1 * shape.param1;
            case "RECTANGLE":
                return shape.param1 * shape.param2;
            case "TRIANGLE":
                return 0.5 * shape.param1 * shape.param2;
            default:
                throw new IllegalArgumentException("Unknown shape: " + shape.type);
        }
    }

    static double calculatePerimeter(Shape shape) {
        switch (shape.type) {
            case "CIRCLE":
                return 2 * Math.PI * shape.param1;
            case "RECTANGLE":
                return 2 * (shape.param1 + shape.param2);
            case "TRIANGLE":
                // Simplified: assumes right triangle
                double hyp = Math.sqrt(shape.param1 * shape.param1
                        + shape.param2 * shape.param2);
                return shape.param1 + shape.param2 + hyp;
            default:
                throw new IllegalArgumentException("Unknown shape: " + shape.type);
        }
    }

    static String describe(Shape shape) {
        switch (shape.type) {
            case "CIRCLE":
                return "Circle with radius " + shape.param1;
            case "RECTANGLE":
                return "Rectangle " + shape.param1 + "x" + shape.param2;
            case "TRIANGLE":
                return "Triangle with base " + shape.param1
                        + " and height " + shape.param2;
            default:
                return "Unknown shape";
        }
    }

    /**
     * Demonstrates the Switch Statement code smell.
     */
    public static void demo() {
        System.out.println("  [Switch Statement - BEFORE refactoring]");
        System.out.println("  Type codes + switch blocks for every operation:");
        System.out.println();

        Shape[] shapes = {
            new Shape("CIRCLE", 5, 0),
            new Shape("RECTANGLE", 4, 6),
            new Shape("TRIANGLE", 3, 4)
        };

        for (Shape s : shapes) {
            System.out.println("    " + describe(s));
            System.out.println("      Area:      "
                    + String.format("%.2f", calculateArea(s)));
            System.out.println("      Perimeter: "
                    + String.format("%.2f", calculatePerimeter(s)));
        }

        System.out.println();
        System.out.println("    Problem: Adding a Pentagon requires modifying");
        System.out.println("    calculateArea(), calculatePerimeter(), describe(),");
        System.out.println("    and every other switch block in the codebase.");
    }
}
