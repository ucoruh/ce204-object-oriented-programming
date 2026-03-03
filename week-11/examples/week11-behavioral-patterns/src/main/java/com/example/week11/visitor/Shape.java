package com.example.week11.visitor;

/**
 * Visitor Pattern - Element Interface
 *
 * Declares the accept() method that takes a visitor as an argument.
 * Each concrete element implements accept() and calls the appropriate
 * visit method on the visitor (double dispatch).
 *
 * Structure:
 *   <<interface>> Shape (Element)
 *       + accept(visitor: ShapeVisitor): void
 *
 * Double Dispatch:
 *   shape.accept(visitor) calls visitor.visit(this), resolving both
 *   the concrete element type AND the concrete visitor type at runtime.
 */
public interface Shape {

    /**
     * Accepts a visitor, which will perform an operation on this shape.
     * The concrete shape calls the appropriate visit method on the visitor.
     *
     * @param visitor the visitor to accept
     */
    void accept(ShapeVisitor visitor);
}
