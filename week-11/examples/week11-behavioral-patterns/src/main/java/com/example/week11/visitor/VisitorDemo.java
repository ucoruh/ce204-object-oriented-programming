package com.example.week11.visitor;

import java.util.Arrays;
import java.util.List;

/**
 * Visitor Pattern - Demo
 *
 * Intent:
 *   Represent an operation to be performed on the elements of an object
 *   structure. Visitor lets you define a new operation without changing
 *   the classes of the elements on which it operates.
 *
 * Structure:
 *   <<interface>> Shape (Element)          <<interface>> ShapeVisitor
 *       + accept(visitor)                      + visit(Circle)
 *       ^       ^                              + visit(Rectangle)
 *       |       |                              ^          ^
 *    Circle  Rectangle                         |          |
 *                                      AreaCalculator  DrawingVisitor
 *
 *   Double Dispatch:
 *     shape.accept(visitor) --> visitor.visit(this)
 *     First dispatch: shape.accept() (polymorphic on shape type)
 *     Second dispatch: visitor.visit(concreteShape) (overloaded on parameter type)
 *
 * When to Use:
 *   - An object structure contains many classes with differing interfaces
 *   - You want to perform many distinct, unrelated operations on these objects
 *   - The object structure rarely changes, but you often define new operations
 *
 * Real-World Examples:
 *   - Compiler AST visitors (type checking, code generation, optimization)
 *   - Document export (render to PDF, HTML, plain text)
 *   - File system operations (calculate size, search, compress)
 *   - Java's FileVisitor for walking file trees
 */
public class VisitorDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 10: VISITOR");
        System.out.println("  Add new operations without changing element classes");
        System.out.println("==============================================================");

        // Create a collection of shapes
        List<Shape> shapes = Arrays.asList(
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Circle(3.0),
            new Rectangle(2.0, 8.0)
        );

        // Operation 1: Calculate areas using AreaCalculator visitor
        System.out.println("\n  --- Visitor 1: Area Calculator ---");
        AreaCalculator areaCalculator = new AreaCalculator();
        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }
        System.out.printf("    Total area of all shapes: %.2f%n", areaCalculator.getTotalArea());

        // Operation 2: Draw shapes using DrawingVisitor
        System.out.println("\n  --- Visitor 2: Drawing Visitor ---");
        DrawingVisitor drawingVisitor = new DrawingVisitor();
        for (Shape shape : shapes) {
            shape.accept(drawingVisitor);
        }

        System.out.println("\n  Key Insight: We added TWO different operations (area, draw)");
        System.out.println("  to shapes WITHOUT modifying Circle or Rectangle classes.");

        System.out.println();
    }
}
