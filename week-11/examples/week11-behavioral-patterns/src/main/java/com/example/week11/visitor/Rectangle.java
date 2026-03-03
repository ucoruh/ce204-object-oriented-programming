package com.example.week11.visitor;

/**
 * Visitor Pattern - Concrete Element: Rectangle
 *
 * A concrete shape element. It implements accept() by calling
 * visitor.visit(this), enabling the visitor to operate on it
 * with full knowledge of its concrete type.
 */
public class Rectangle implements Shape {

    /** The width of the rectangle */
    private final double width;

    /** The height of the rectangle */
    private final double height;

    /**
     * Creates a Rectangle with the given width and height.
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of this rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of this rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Accepts a visitor. Calls visit(this) so the visitor knows
     * it is visiting a Rectangle (double dispatch).
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
