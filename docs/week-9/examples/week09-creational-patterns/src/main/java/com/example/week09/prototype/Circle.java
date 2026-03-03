package com.example.week09.prototype;

/**
 * Concrete Prototype - Circle
 *
 * A circle shape with a radius. Implements the cloneShape() method
 * to create a copy of itself, including both inherited (color, x, y)
 * and its own fields (radius).
 */
public class Circle extends Shape {

    private double radius;

    public Circle() {
    }

    /**
     * Copy constructor -- copies all fields from the source circle.
     */
    public Circle(Circle source) {
        super(source);            // Copy base class fields
        this.radius = source.radius;  // Copy circle-specific fields
    }

    /**
     * Prototype clone method. Returns a new Circle with the same state.
     */
    @Override
    public Shape cloneShape() {
        return new Circle(this);
    }

    // --- Getters and Setters ---

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Circle other = (Circle) obj;
        return Double.compare(other.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("Circle{color='%s', x=%d, y=%d, radius=%.1f}",
                getColor(), getX(), getY(), radius);
    }
}
