package com.example.week09.prototype;

/**
 * Concrete Prototype - Rectangle
 *
 * A rectangle shape with width and height. Implements cloneShape()
 * to produce a copy with the same dimensions and base properties.
 */
public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle() {
    }

    /**
     * Copy constructor -- copies all fields from the source rectangle.
     */
    public Rectangle(Rectangle source) {
        super(source);               // Copy base class fields
        this.width = source.width;   // Copy rectangle-specific fields
        this.height = source.height;
    }

    /**
     * Prototype clone method. Returns a new Rectangle with the same state.
     */
    @Override
    public Shape cloneShape() {
        return new Rectangle(this);
    }

    // --- Getters and Setters ---

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Rectangle other = (Rectangle) obj;
        return Double.compare(other.width, width) == 0
                && Double.compare(other.height, height) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("Rectangle{color='%s', x=%d, y=%d, width=%.1f, height=%.1f}",
                getColor(), getX(), getY(), width, height);
    }
}
