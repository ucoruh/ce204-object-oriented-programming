package com.example.week11.visitor;

/**
 * Visitor Pattern - Concrete Element: Circle
 *
 * A concrete shape element. It implements accept() by calling
 * visitor.visit(this), enabling the visitor to operate on it
 * with full knowledge of its concrete type.
 */
public class Circle implements Shape {

    /** The radius of the circle */
    private final double radius;

    /**
     * Creates a Circle with the given radius.
     *
     * @param radius the radius of the circle
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the radius of this circle.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Accepts a visitor. Calls visit(this) so the visitor knows
     * it is visiting a Circle (double dispatch).
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
