package com.example.week11.visitor;

/**
 * Visitor Pattern - Concrete Visitor: Drawing Visitor
 *
 * Simulates drawing each shape it visits. This is another operation
 * added to shapes without modifying the Shape classes, demonstrating
 * how new behaviors can be added by creating new visitors.
 */
public class DrawingVisitor implements ShapeVisitor {

    /**
     * "Draws" a circle (simulated with console output).
     *
     * @param circle the circle to draw
     */
    @Override
    public void visit(Circle circle) {
        System.out.printf("    [DrawingVisitor] Drawing Circle with radius %.1f at origin%n",
            circle.getRadius());
        System.out.println("    [DrawingVisitor]   -> Rendering circular path...");
    }

    /**
     * "Draws" a rectangle (simulated with console output).
     *
     * @param rectangle the rectangle to draw
     */
    @Override
    public void visit(Rectangle rectangle) {
        System.out.printf("    [DrawingVisitor] Drawing Rectangle %.1f x %.1f at origin%n",
            rectangle.getWidth(), rectangle.getHeight());
        System.out.println("    [DrawingVisitor]   -> Rendering rectangular path...");
    }
}
