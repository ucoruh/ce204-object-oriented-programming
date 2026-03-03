package com.example.week11.visitor;

/**
 * Visitor Pattern - Visitor Interface
 *
 * Declares a visit method for each concrete element type. Adding
 * a new operation on shapes means creating a new visitor that
 * implements all visit methods -- without modifying the shape classes.
 *
 * Structure:
 *   <<interface>> ShapeVisitor
 *       + visit(circle: Circle): void
 *       + visit(rectangle: Rectangle): void
 *
 * Open/Closed Principle:
 *   - Open for new operations: just add a new visitor
 *   - Closed for modification: existing shape classes don't change
 *   - Trade-off: adding a new shape type requires updating all visitors
 */
public interface ShapeVisitor {

    /**
     * Visits a Circle element and performs an operation on it.
     *
     * @param circle the circle to visit
     */
    void visit(Circle circle);

    /**
     * Visits a Rectangle element and performs an operation on it.
     *
     * @param rectangle the rectangle to visit
     */
    void visit(Rectangle rectangle);
}
